package com.czl.core;


import com.czl.annotations.DealModifyChange;
import com.czl.constants.DealOperationType;
import com.czl.constants.OperatorHistoryConstant;
import com.czl.util.AssertHelper;
import com.czl.util.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 操作历史生成器
 *
 * @author CaiZelin
 * @date 2022/6/29 20:57
 */
public class OperationRecordGenerator {

    private HashMap<String, List<String>> contentMap = new HashMap<>();

    // todo A B -> same Obj
    /**
     * 生成操作历史
     *
     * @param before
     * @param after
     * @throws IllegalAccessException
     */
    public void operationRecorder(Object before, Object after) throws IllegalAccessException {
        if (Objects.isNull(before) && Objects.isNull(after)) {
            return;
        }

        Field[] declaredFields = before.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(DealModifyChange.class)) {
                continue;
            }
            DealModifyChange annotation = field.getAnnotation(DealModifyChange.class);
            DealOperationType dealOperationType = annotation.value();
            field.setAccessible(true);
            Class<?> type = field.getType();

            Object fieldBefore = field.get(before);

            Object fieldAfter = field.get(after);


            if (BeanUtils.isBasicType(type)) {
                // 1. 基本数据类型
                String content = this.compareAndGenerateDealOperationContent(dealOperationType, fieldBefore, fieldAfter);
                if (null == content) {
                    continue;
                }
                // 填充历史记录到map中
                this.fillContenInMap(dealOperationType, content);

            } else if (type == List.class) {
                // 2. 列表类型
                List list = (List) fieldBefore;
                List listAfter = (List) fieldAfter;
                // 生成操作历史内容
                String content = compareAndGenerateDealOperationContent(dealOperationType, list, listAfter);
                if (null == content) {
                    continue;
                }
                // 填充历史记录到map中
                this.fillContenInMap(dealOperationType, content);

            } else {
                // 3. 引用数据类型
                this.operationRecorder(fieldBefore, fieldAfter);
            }
        }
    }

    /**
     * 填充操作历史到map中
     *
     * @param dealOperationType
     * @param content
     */
    private void fillContenInMap(DealOperationType dealOperationType, String content) {
        // 父类已存在
        if (null == contentMap.get(dealOperationType.getCategory().getName())) {
            ArrayList<String> contentList = new ArrayList<>();
            contentList.add(content);
            contentMap.put(dealOperationType.getCategory().getName(), contentList);
            return;
        }
        // 父类不存在
        List<String> contentList = contentMap.get(dealOperationType.getCategory().getName());
        contentList.add(content);
        contentMap.put(dealOperationType.getCategory().getName(), contentList);

    }

    /**
     * 生成操作历史内容
     *
     * @param dealOperationType
     * @param before
     * @param after
     * @return
     */
    protected String compareAndGenerateDealOperationContent(DealOperationType dealOperationType, Object before, Object after) {
        // 传入list类型
        if (before instanceof List || after instanceof List) {
            // add
            if (AssertHelper.isEmpty((List) before) && AssertHelper.isNotEmpty((List) after)) {
                String afterStr = genSingleRecord((List) after);
                String content = String.format(OperatorHistoryConstant.DealReport.CREATE_DEAL_TEXT, dealOperationType.getName(), afterStr);
                return content;
            }

            // delete
            if (AssertHelper.isNotEmpty((List) before) && AssertHelper.isEmpty((List) after)) { // 删除
                String beforeStr = genSingleRecord((List) before);
                String content = String.format(OperatorHistoryConstant.DealReport.DELETE_DEAL_TEXT, dealOperationType.getName(), beforeStr);
                return content;

            }

            // update
            if (!Objects.equals(before, after)) {
                String beforeStr = genSingleRecord((List) before);
                String afterStr = genSingleRecord((List) after);
                String content = String.format(OperatorHistoryConstant.DealReport.UPDATE_DEAL_TEXT, dealOperationType.getName(), beforeStr, afterStr);
                return content;
            }
        }

        // 传入基本数据类型
        if (!Objects.equals(before, after)) {

            String beforeStr = AssertHelper.isEmpty(before)
                    ? "-" : before.toString();
            String afterStr = AssertHelper.isEmpty(after)
                    ? "-" : after.toString();

            String content = String.format(OperatorHistoryConstant.DealReport.UPDATE_DEAL_TEXT, dealOperationType.getName(), beforeStr, afterStr);

            return content;
        }

        return null;

    }

    /**
     * 生成单条操作历史
     *
     * @param list
     * @return
     */
    private String genSingleRecord(List list) {

        StringBuilder builder = new StringBuilder();
        if (list.get(0) instanceof DealCustomerDTO) { // 业主信息
            ArrayList<DealCustomerDTO> newList = new ArrayList<DealCustomerDTO>(list);
            if (newList.size() == 1) {
                builder.append("客户“" + newList.get(0).toOperationRecordString() + "”");
                return builder.toString();
            }
            for (int i = 0; i < newList.size(); i++) {
                builder.append("客户" + (i + 1) + "“" + newList.get(i).toOperationRecordString() + "”、");
            }

        }

        int lastSplitInd = builder.lastIndexOf("、");
        if (-1 != lastSplitInd) {
            return builder.deleteCharAt(lastSplitInd).toString();
        }
        return builder.toString();
    }
}

package com.czl.constants;

/**
 * 操作历史文案
 *
 * @ClassName OperatorHistoryConstant
 * @createTime 2021年08月24日
 */
public class OperatorHistoryConstant {

    /**
     *  成交
     */
    public static class Deal {
        public final static String ADD_DEAL_REPORT_TEXT = "「%s」新增成交";
        public final static String ADD_DATAGRAM_TEXT = "新增电子资料：上传「%s」%d张";
        public final static String REMOVE_DATAGRAM_TEXT = "删除电子资料：删除「%s」%d张";
        public final static String DEAL_REPORT_APPROVED = "成交报告审批通过";
        public final static String DEAL_REPORT_REJECT = "成交报告审批拒绝";
    }




    /**
     * 交易修改
     */
    public static class DealReport {
        public final static String UPDATE_DEAL_PREFIX = "修改「%s」\n%s";
        public final static String UPDATE_DEAL_TEXT = "%s由「%s」修改为「%s」";

        public final static String DELETE_DEAL_TEXT = "删除%s「%s」";
        public final static String CREATE_DEAL_TEXT = "新增%s「%s」";
        
        public final static String UPDATE_DEALMARK_TEXT = "修改「备注」:\n备注由「%s」修改为「%s」";
        public final static String UPDATE_DEAL_MANAGER = "修改「交易专员」:\n交易专员由「%s」修改为「%s」";
        public final static String UPDATE_DEAL_ACHIEVEMENT_REMARK = "修改「业绩备注」:\n业绩备注由「%s」修改为「%s」";

    }



} 


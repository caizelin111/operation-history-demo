package com.czl.constants;

import static com.constants.DealOperationCategory.*;

/**
 * @date 10:56 2021/8/27
 * @description
 */
public enum DealOperationType implements NumberEnum {
    DEFAULT(null,-1,"交易操作类型枚举标识"),

    /** 成交信息属性枚举对象 */
    DEAL_TEAM_USER(DEAL_INFO, 1, "成交人"),
    HOUSE_OWNER_INFO(DEAL_INFO,2,"业主信息"),
    HOUSE_CUSTOM_INFO(DEAL_INFO,3,"客户信息"),
    CONTRACT_CODE(DEAL_INFO, 19, "合同编号"),

    /** 二手交易属性枚举对象  */
    SecondHand_DEAL_PRICE(SIGN_INFO,4,"成交总价"),
    SecondHand_SIGN_DATE(SIGN_INFO,5,"签约日期"),
    SecondHand_PAY_TYPE(SIGN_INFO,6,"付款方式"),
    SecondHand_APPLY_LOAN_DATE(SIGN_INFO,7,"申请贷款日期"),
    SecondHand_FUND_SUPERVISION_DATE(SIGN_INFO,8,"资金监管日期"),
    SecondHand_PROPERTY_STAE(SIGN_INFO,9,"产权状况"),
    SecondHand_RELEASE_MORTGAGE_DATE(SIGN_INFO,10,"解除抵押日期"),
    SecondHand_APPLY_TRANSFER_DATE(SIGN_INFO,11,"申请过户日期"),
    
    /** 租房交易属性枚举对象*/
    RENT_DEAL_PRICE(SIGN_INFO,12,"成交租金"),
    RENT_SIGN_DATE(SIGN_INFO,13,"签约日期"),
    RENT_START_DATE(SIGN_INFO,14,"出租日期"),
    RENT_END_DATE(SIGN_INFO,15,"租约到期日期"),
    
    /** 新房交易属性枚举对象*/
    BRANDNEW_DEAL_PRICE(SIGN_INFO,16,"成交总价"),
    BRANDNEW_SIGN_DATE(SIGN_INFO,17,"签约日期"),
    BRANDNEW_PAY_TYPE(SIGN_INFO,18,"付款方式"),
    BRANDNEW_CONFIRM_DATE(SIGN_INFO,19,"认购日期"),

    DEARR_MARK(SIGN_INFO,20,"交易备注"),

    RECEIVABLE_FUNDS(DEAL_RECEIVABLEFUND,20,"应收款项"),
    DEDUCTIONS(DEAL_DEDUCTION,21,"扣除款项"),
    DIVIDES(DEAL_DIVIDE,22,"业绩分成"),
    DEAL_MANAGER_AGENTIDS(DEAL_MANAGER,23,"交易专员"),
    ACHIEVEMENT_REMARK(DEAL_ACHIEVEMENT_REMARK, 24, "业绩备注"),

    DATAGRAMS(DEAL_DATAGRAM, 30, "电子资料"),

    /**
     * 自定义交易签约属性
     */
    CUSTOM_DEAL_PRICE(SIGN_INFO, 40, "交易金额"),
    CUSTOM_DEAL_SIGN_DATE(SIGN_INFO, 41, "签约日期"),
    ;
    
    private DealOperationCategory category;
    private int type;
    private String name;

    DealOperationType(DealOperationCategory category, int type, String name) {
        this.category = category;
        this.type = type;
        this.name = name;
    }

    @Override
    public int getCode() {
        return type;
    }

    public String getName() {
        return name;
    }

    public DealOperationCategory getCategory() {
        return category;
    }
}

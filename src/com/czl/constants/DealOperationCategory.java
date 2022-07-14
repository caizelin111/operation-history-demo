package com.czl.constants;

/**
 * @date 10:57 2021/8/27
 * @description
 */
public enum DealOperationCategory implements NumberEnum {
    DEAL_INFO(1, "成交信息"),
    SIGN_INFO(2,"签约信息"),
    DEAL_RECEIVABLEFUND(3,"应收款项"),
    DEAL_DEDUCTION(4,"扣除款项"),
    DEAL_DIVIDE(5,"业绩分配"),
    DEAL_MANAGER(6,"交易专员"),
    DEAL_DATAGRAM(7,"电子资料"),
    DEAL_ACHIEVEMENT_REMARK(8, "业绩备注"),
    ;

    private int category;

    private String name;

    DealOperationCategory(int category, String name) {
        this.category = category;
        this.name = name;
    }

    @Override
    public int getCode() {
        return category;
    }

    public String getName() {
        return name;
    }

}

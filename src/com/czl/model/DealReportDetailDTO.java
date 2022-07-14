package com.czl.model;


import com.annotations.DealModifyChange;
import com.constants.DealOperationType;


/**
 * @date 20:52 2021/8/23
 */
public class DealReportDetailDTO {

    @DealModifyChange(DealOperationType.CONTRACT_CODE)
    private String contractCode;

    /**
     * 交易编号
     */
    private String dealNo;

    /**
     * 成交人
     */
    @DealModifyChange(DealOperationType.DEFAULT)
    private TeamUserDTO dealTeamUser;


    /**
     * 成交业主
     */
    @DealModifyChange(DealOperationType.HOUSE_OWNER_INFO)
    private List<DealHouseOwnerDTO> dealHouseOwners;

    /**
     * 成交客户
     */
    @DealModifyChange(DealOperationType.HOUSE_CUSTOM_INFO)
    private List<DealCustomerDTO> dealCustomers;


}

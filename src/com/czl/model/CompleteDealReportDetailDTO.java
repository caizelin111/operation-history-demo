package com.czl.model;


import com.czl.annotations.DealModifyChange;
import com.czl.constants.DealOperationType;

import java.util.List;

public class CompleteDealReportDetailDTO {

    @DealModifyChange(DealOperationType.DEFAULT)
    private DealReportDetailDTO dealDetail;


    @DealModifyChange(DealOperationType.DATAGRAMS)
    private List<DealDatagramDTO> datagrams;

}
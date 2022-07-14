package com.czl.model;

import java.util.List;

/**
 * @date 21:33 2021/8/23
 */
public class DealDatagramDTO {


    private List<String> urls;

    public DealDatagramDTO() {
    }

    public DealDatagramDTO(List<String> urls) {
        this.urls = urls;
    }

    public String toOperationRecordString() {
        StringBuilder str = new StringBuilder(
                (null == urls ? "" : urls.size() + "张"));

        return str.toString();

    }
}

package com.hieuminh.controller.androidAPI.dto;

import java.io.Serializable;

public class CountBillDTO implements Serializable {
    Integer count;

    public CountBillDTO(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}

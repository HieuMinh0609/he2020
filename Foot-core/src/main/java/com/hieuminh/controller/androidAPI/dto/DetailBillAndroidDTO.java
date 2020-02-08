package com.hieuminh.controller.androidAPI.dto;

import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.DetailBillDTO;

import java.io.Serializable;
import java.util.List;

public class DetailBillAndroidDTO implements Serializable {
    private  Integer id;
    private List<DetailBillDTO> detailBillAndroidDTOS;
    private BillDTO billDTO;



    public List<DetailBillDTO> getDetailBillAndroidDTOS() {
        return detailBillAndroidDTOS;
    }

    public void setDetailBillAndroidDTOS(List<DetailBillDTO> detailBillAndroidDTOS) {
        this.detailBillAndroidDTOS = detailBillAndroidDTOS;
    }


    public BillDTO getBillDTO() {
        return billDTO;
    }

    public void setBillDTO(BillDTO billDTO) {
        this.billDTO = billDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

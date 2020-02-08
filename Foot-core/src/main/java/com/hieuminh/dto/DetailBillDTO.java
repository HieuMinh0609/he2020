package com.hieuminh.dto;


public class DetailBillDTO  extends  AbstractDTO<DetailBillDTO>  {
    private Integer idDetailBill;
    private BillDTO idDetailBillBillEntity;
    private ProductDTO idDBillProductEntity;
    private Integer count;


    public Integer getIdDetailBill() {
        return idDetailBill;
    }

    public void setIdDetailBill(Integer idDetailBill) {
        this.idDetailBill = idDetailBill;
    }

    public BillDTO getIdDetailBillBillEntity() {
        return idDetailBillBillEntity;
    }

    public void setIdDetailBillBillEntity(BillDTO idDetailBillBillEntity) {
        this.idDetailBillBillEntity = idDetailBillBillEntity;
    }

    public ProductDTO getIdDBillProductEntity() {
        return idDBillProductEntity;
    }

    public void setIdDBillProductEntity(ProductDTO idDBillProductEntity) {
        this.idDBillProductEntity = idDBillProductEntity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }




}

package com.hieuminh.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "detailbill")
public class DetailBillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[iddetailbill]")
    private Integer idDetailBill;

    @ManyToOne
    @JoinColumn(name = "[idbill]")
    private BillEntity idDetailBillBillEntity;

    @ManyToOne
    @JoinColumn(name = "[idproduct]")
    private ProductEntity idDBillProductEntity;

    @Column(name = "[count]")
    private Integer count;



    public Integer getIdDetailBill() {
        return idDetailBill;
    }

    public void setIdDetailBill(Integer idDetailBill) {
        this.idDetailBill = idDetailBill;
    }

    public BillEntity getIdDetailBillBillEntity() {
        return idDetailBillBillEntity;
    }

    public void setIdDetailBillBillEntity(BillEntity idDetailBillBillEntity) {
        this.idDetailBillBillEntity = idDetailBillBillEntity;
    }

    public ProductEntity getIdDBillProductEntity() {
        return idDBillProductEntity;
    }

    public void setIdDBillProductEntity(ProductEntity idDBillProductEntity) {
        this.idDBillProductEntity = idDBillProductEntity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }




}

package com.hieuminh.entity;


import javax.persistence.*;

@Entity
@Table(name = "detailsale")
public class DetailSaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[iddetailsale]")
    private Integer idDetailSale;


    @ManyToOne
    @JoinColumn(name = "[idsale]")
    private SaleEntity idSaleEntity;

    @ManyToOne
    @JoinColumn(name = "[idproduct]")
    private ProductEntity idProductSaleEntity;

    @Column(name = "[downpercen]")
    private Integer downpercen;



    public Integer getIdDetailSale() {
        return idDetailSale;
    }

    public void setIdDetailSale(Integer idDetailSale) {
        this.idDetailSale = idDetailSale;
    }

    public SaleEntity getIdSaleEntity() {
        return idSaleEntity;
    }

    public void setIdSaleEntity(SaleEntity idSaleEntity) {
        this.idSaleEntity = idSaleEntity;
    }

    public ProductEntity getIdProductSaleEntity() {
        return idProductSaleEntity;
    }

    public void setIdProductSaleEntity(ProductEntity idProductSaleEntity) {
        this.idProductSaleEntity = idProductSaleEntity;
    }


    public Integer getDownpercen() {
        return downpercen;
    }

    public void setDownpercen(Integer downpercen) {
        this.downpercen = downpercen;
    }
}

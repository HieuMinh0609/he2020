package com.hieuminh.dto;

public class DetailSaleDTO extends AbstractDTO<DetailSaleDTO> {


    private Integer idDetailSale;
    private SaleDTO idSaleEntity;
    private ProductDTO idProductSaleEntity;
    private Integer downpercen;





    public Integer getIdDetailSale() {
        return idDetailSale;
    }

    public void setIdDetailSale(Integer idDetailSale) {
        this.idDetailSale = idDetailSale;
    }

    public SaleDTO getIdSaleEntity() {
        return idSaleEntity;
    }

    public void setIdSaleEntity(SaleDTO idSaleEntity) {
        this.idSaleEntity = idSaleEntity;
    }

    public ProductDTO getIdProductSaleEntity() {
        return idProductSaleEntity;
    }

    public void setIdProductSaleEntity(ProductDTO idProductSaleEntity) {
        this.idProductSaleEntity = idProductSaleEntity;
    }

    public Integer getDownpercen() {
        return downpercen;
    }

    public void setDownpercen(Integer downpercen) {
        this.downpercen = downpercen;
    }


}

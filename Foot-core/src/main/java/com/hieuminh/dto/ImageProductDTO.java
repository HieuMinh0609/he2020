package com.hieuminh.dto;

public class ImageProductDTO  extends AbstractDTO<ImageProductDTO> {

    private Integer idImageProduct;
    private ProductDTO idImageProductEntity;
    private String images;




    public Integer getIdImageProduct() {
        return idImageProduct;
    }

    public void setIdImageProduct(Integer idImageProduct) {
        this.idImageProduct = idImageProduct;
    }

    public ProductDTO getIdImageProductEntity() {
        return idImageProductEntity;
    }

    public void setIdImageProductEntity(ProductDTO idImageProductEntity) {
        this.idImageProductEntity = idImageProductEntity;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }



}

package com.hieuminh.dto;

import java.util.List;

public class ProductOpenDTO extends AbstractDTO<ProductOpenDTO> {

    private Integer idProduct;
    private String nameProduct;
    private Integer cost;
    private String information;
    private TypeDTO typeIdEntity;
    private String image;
    private Integer view;
    private Integer status;
    private Integer comment;
    private List<ImageProductDTO> imageProductDTOS;
    private Integer downpercen;
    private Integer rating;



    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public TypeDTO getTypeIdEntity() {
        return typeIdEntity;
    }

    public void setTypeIdEntity(TypeDTO typeIdEntity) {
        this.typeIdEntity = typeIdEntity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDownpercen() {
        return downpercen;
    }

    public void setDownpercen(Integer downpercen) {
        this.downpercen = downpercen;
    }


    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public List<ImageProductDTO> getImageProductDTOS() {
        return imageProductDTOS;
    }

    public void setImageProductDTOS(List<ImageProductDTO> imageProductDTOS) {
        this.imageProductDTOS = imageProductDTOS;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}

package com.hieuminh.dto;

import java.util.List;

public class ProductDTO extends AbstractDTO<ProductDTO> {

    private Integer idProduct;
    private String nameProduct;
    private Integer cost;
    private String information;
    private TypeDTO typeIdEntity;
    private String image;
    private Integer view;
    private Integer status;

    List<ImportProductDTO> importProductDTOS;
    public List<ImportProductDTO> getImportProductDTOS() {
        return importProductDTOS;
    }

    public void setImportProductDTOS(List<ImportProductDTO> importProductDTOS) {
        this.importProductDTOS = importProductDTOS;
    }



    private List<TypeDTO> typeDTOList;
    private Integer typeId ;


    public List<TypeDTO> getTypeDTOList() {
        return typeDTOList;
    }

    public void setTypeDTOList(List<TypeDTO> typeDTOList) {
        this.typeDTOList = typeDTOList;
    }


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


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
}

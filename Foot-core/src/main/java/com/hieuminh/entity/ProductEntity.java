package com.hieuminh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idproduct]")
    private Integer idProduct;

    @Column(name = "[nameproduct]")
    private String nameProduct;

    @Column(name = "[cost]")
    private Integer cost;

    @Column(name = "[information]")
    private String information;

    @OneToMany(mappedBy ="idImageProductEntity" ,fetch = FetchType.LAZY)
    private List<ImageProductEntity> imageProductEntityList ;

    @OneToMany(mappedBy ="idDBillProductEntity" ,fetch = FetchType.LAZY)
    private List<DetailBillEntity> productDBillEntityList;


    @OneToMany(mappedBy ="idProductSaleEntity" ,fetch = FetchType.LAZY)
    private List<DetailSaleEntity> detailSaleEntityList;

    @OneToMany(mappedBy ="idProductEntity" ,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    @ManyToOne
    @JoinColumn(name = "[typeid]")
    private TypeEntity typeIdEntity;

    @Column(name = "[image]")
    private String image;

    @Column(name = "[view]")
    private Integer view;

    @Column(name = "[status]")
    private Integer status;




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


    public List<ImageProductEntity> getImageProductEntityList() {
        return imageProductEntityList;
    }

    public void setImageProductEntityList(List<ImageProductEntity> imageProductEntityList) {
        this.imageProductEntityList = imageProductEntityList;
    }

    public List<DetailSaleEntity> getDetailSaleEntityList() {
        return detailSaleEntityList;
    }

    public void setDetailSaleEntityList(List<DetailSaleEntity> detailSaleEntityList) {
        this.detailSaleEntityList = detailSaleEntityList;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    public TypeEntity getTypeIdEntity() {
        return typeIdEntity;
    }

    public void setTypeIdEntity(TypeEntity typeIdEntity) {
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


    public List<DetailBillEntity> getProductDBillEntityList() {
        return productDBillEntityList;
    }

    public void setProductDBillEntityList(List<DetailBillEntity> productDBillEntityList) {
        this.productDBillEntityList = productDBillEntityList;
    }
}

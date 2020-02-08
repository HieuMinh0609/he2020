package com.hieuminh.entity;


import javax.persistence.*;

@Entity
@Table(name = "imageproduct")
public class ImageProductEntity {
    private static final long serialVersionUID = -4907767318320601464L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idimageproduct]")
    private Integer idImageProduct;

    @ManyToOne
    @JoinColumn(name = "[idproduct]")
    private ProductEntity idImageProductEntity;


    @Column(name = "[images]")
    private String images;

    public Integer getIdImageProduct() {
        return idImageProduct;
    }

    public void setIdImageProduct(Integer idImageProduct) {
        this.idImageProduct = idImageProduct;
    }

    public ProductEntity getIdImageProductEntity() {
        return idImageProductEntity;
    }

    public void setIdImageProductEntity(ProductEntity idImageProductEntity) {
        this.idImageProductEntity = idImageProductEntity;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


}

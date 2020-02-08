package com.hieuminh.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type")
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[typeid]")
    private Integer typeId;

    @Column(name = "[typename]")
    private String typeName;

    @OneToMany(mappedBy ="typeIdEntity" ,fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList;


    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


}

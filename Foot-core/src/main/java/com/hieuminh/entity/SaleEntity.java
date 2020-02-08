package com.hieuminh.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "sale")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idsale]")
    private Integer idSale;

    @Column(name = "[name]")
    private String name;

    @OneToMany(mappedBy ="idSaleEntity" ,fetch = FetchType.LAZY)
    private List<DetailSaleEntity> detailSaleEntityList;

    @Column(name = "[timestart]")
    private Timestamp timeStart;

    @Column(name = "[timeend]")
    private Timestamp timeEnd;

    @Column(name = "[Detail]")
    private String detail;

    @Column(name = "[image]")
    private String image;

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public List<DetailSaleEntity> getDetailSaleEntityList() {
        return detailSaleEntityList;
    }

    public void setDetailSaleEntityList(List<DetailSaleEntity> detailSaleEntityList) {
        this.detailSaleEntityList = detailSaleEntityList;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.hieuminh.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcomment")
    private Integer idComment;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private UserEntity idUserEntity;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private ProductEntity idProductEntity;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "datetime")
    private Timestamp dateTime;

    @Column(name = "content")
    private String content;

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public UserEntity getIdUserEntity() {
        return idUserEntity;
    }

    public void setIdUserEntity(UserEntity idUserEntity) {
        this.idUserEntity = idUserEntity;
    }

    public ProductEntity getIdProductEntity() {
        return idProductEntity;
    }

    public void setIdProductEntity(ProductEntity idProductEntity) {
        this.idProductEntity = idProductEntity;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }





}

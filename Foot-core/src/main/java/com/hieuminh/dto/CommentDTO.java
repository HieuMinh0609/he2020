package com.hieuminh.dto;

import java.sql.Timestamp;

public class CommentDTO extends  AbstractDTO<CommentDTO> {

    private Integer idComment;
    private UserDTO idUserEntity;
    private ProductDTO idProductEntity;
    private Integer rate;
    private Timestamp dateTime;
    private String content;

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public UserDTO getIdUserEntity() {
        return idUserEntity;
    }

    public void setIdUserEntity(UserDTO idUserEntity) {
        this.idUserEntity = idUserEntity;
    }

    public ProductDTO getIdProductEntity() {
        return idProductEntity;
    }

    public void setIdProductEntity(ProductDTO idProductEntity) {
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

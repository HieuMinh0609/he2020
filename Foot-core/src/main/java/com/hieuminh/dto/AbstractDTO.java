package com.hieuminh.dto;

import com.hieuminh.dto.utils.PagingModelDTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

public class AbstractDTO<T> extends PagingModelDTO<T> implements Serializable {

    private static final long serialVersionUID = -4396911805210533643L;

    private Long id;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String dateTimeEnd;
    private Integer count;
    private String dateTimeStart;
    private String searchValue;
    private String urlMapping;
    private String message;
    private String[] checkList;
    private Map<String,String> mapStrings;
    private String thumbnailBase64;
    private String[] listImage;




    public String[] getListImage() {
        return listImage;
    }

    public void setListImage(String[] listImage) {
        this.listImage = listImage;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }



    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getCheckList() {
        return checkList;
    }

    public void setCheckList(String[] checkList) {
        this.checkList = checkList;
    }

    public Map<String, String> getMapStrings() {
        return mapStrings;
    }

    public void setMapStrings(Map<String, String> mapStrings) {
        this.mapStrings = mapStrings;
    }

    public String getThumbnailBase64() {
        return thumbnailBase64;
    }

    public void setThumbnailBase64(String thumbnailBase64) {
        this.thumbnailBase64 = thumbnailBase64;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(String dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}

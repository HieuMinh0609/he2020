package com.hieuminh.repository;

import com.hieuminh.entity.ImageProductEntity;

import java.util.List;

public interface ImageProductDao  extends  GenericDao<Integer, ImageProductEntity> {
    List<ImageProductEntity>  findListById(String property, Object value);
    void deleteListImage(String property,Integer value);

}

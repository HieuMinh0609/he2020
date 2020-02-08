package com.hieuminh.repository;

import com.hieuminh.entity.ProductEntity;

import java.util.List;

public interface ProductDao extends  GenericDao<Integer, ProductEntity> {
    List<ProductEntity> findByProduct(List<String> name);

}

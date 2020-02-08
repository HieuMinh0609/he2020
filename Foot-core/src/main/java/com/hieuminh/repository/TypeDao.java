package com.hieuminh.repository;

import com.hieuminh.entity.TypeEntity;

import java.util.List;

public interface TypeDao extends  GenericDao<Integer, TypeEntity> {
    List<TypeEntity> findByTypes(List<String> types);
}

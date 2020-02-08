package com.hieuminh.service;

import com.hieuminh.dto.TypeDTO;
import com.hieuminh.dto.UserDTO;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface TypeService {
    Map<String,String> getTypes();
    List<TypeDTO> findAll();
    TypeDTO findEqualUnique(String property, Object value);
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    TypeDTO findById(Integer id);
    TypeDTO updateType(TypeDTO typeDTO);
    void SaveType(TypeDTO typeDTO);
    Integer deleteType(List<Integer> ids) throws HibernateException;




}

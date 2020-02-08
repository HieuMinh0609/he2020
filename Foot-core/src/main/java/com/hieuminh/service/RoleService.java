package com.hieuminh.service;

import com.hieuminh.dto.RoleDTO;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Object[] findByPropertyMapNotLike(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    RoleDTO findById(Integer id);
    void save(RoleDTO roleDTO);
    RoleDTO update(RoleDTO roleDTO);
    Integer delete(List<Integer> ids) throws HibernateException;
    List<RoleDTO> findAll();
}

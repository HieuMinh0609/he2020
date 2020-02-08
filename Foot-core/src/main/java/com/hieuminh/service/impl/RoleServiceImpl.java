package com.hieuminh.service.impl;

import com.hieuminh.converter.RoleConverter;
import com.hieuminh.converter.SaleConverter;
import com.hieuminh.dto.RoleDTO;
import com.hieuminh.entity.RoleEntity;
import com.hieuminh.entity.SaleEntity;
import com.hieuminh.repository.RoleDao;
import com.hieuminh.service.RoleService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("RoleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;


    @Override
    public Object[] findByPropertyMapNotLike(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= roleDao.findByPropertyMapNotLike(property,sortExpression,sortDirection,offset,limit);
        List<RoleDTO> list = new ArrayList<RoleDTO>();
        for(RoleEntity roleEntity: (List<RoleEntity>) objects[1]){
            RoleDTO transporterDTO = RoleConverter.entityToDto(roleEntity);
            list.add(transporterDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public RoleDTO findById(Integer id) {
        RoleEntity entity = roleDao.findById(id) ;
        RoleDTO dto = RoleConverter.entityToDto(entity);
        return  dto;
    }

    @Override
    public void save(RoleDTO roleDTO) {
        RoleEntity entity = RoleConverter.dtoToEntity(roleDTO);
        roleDao.save(entity);
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        RoleEntity roleEntity = RoleConverter.dtoToEntity(roleDTO);
        roleEntity = roleDao.update(roleEntity);
        roleDTO = RoleConverter.entityToDto(roleEntity);
        return roleDTO;
    }

    @Override
    public Integer delete(List<Integer> ids) throws HibernateException {
        Integer result = roleDao.delete(ids);
        return result;
    }

    @Override
    public List<RoleDTO> findAll() {
        List<RoleEntity> entity = roleDao.findAll() ;
        List<RoleDTO>  roleDTOS = new ArrayList<>();
        for(RoleEntity item:entity){
            roleDTOS.add(RoleConverter.entityToDto(item));
        }
        return  roleDTOS;
    }
}

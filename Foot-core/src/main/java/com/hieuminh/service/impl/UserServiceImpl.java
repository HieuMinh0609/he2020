package com.hieuminh.service.impl;


import com.hieuminh.converter.UserConverter;
import com.hieuminh.dto.CheckLogin;
import com.hieuminh.dto.RoleDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.entity.UserEntity;
import com.hieuminh.repository.UserDao;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.StringGenerate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;



    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
         Object[] objects= userDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
         List<UserDTO> list = new ArrayList<>();
         for(UserEntity userEntity: (List<UserEntity>) objects[1]){
            UserDTO userDTO = UserConverter.entityToDto(userEntity);
            list.add(userDTO);
         }
         objects[1]=list;
         return objects;
    }

    @Override
    public Object[] findByPropertyMapNotLike(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= userDao.findByPropertyMapNotLike(property,sortExpression,sortDirection,offset,limit);
        List<UserDTO> list = new ArrayList<>();
        for(UserEntity userEntity: (List<UserEntity>) objects[1]){
            UserDTO userDTO = UserConverter.entityToDto(userEntity);
            list.add(userDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public UserDTO findById(Integer idUser) {
        UserEntity entity = userDao.findById(idUser) ;
        UserDTO dto = UserConverter.entityToDto(entity);
        return  dto;
    }

    @Override
    public void SaveUser(UserDTO userDTO) {

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIdRole(2);
        userDTO.setIdRoleEntity(roleDTO);
        userDTO.setBlock(0);
        UserEntity entity = UserConverter.dtoToEntity(userDTO);
        userDao.save(entity);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
       // userDTO.setBlock(userDao.findById(userDTO.getIdUser()).getBlock());
        UserEntity userEntity = UserConverter.dtoToEntity(userDTO);
         userEntity = userDao.update(userEntity);
         userDTO = UserConverter.entityToDto(userEntity);
        return userDTO;

    }

    @Override
    public Integer deleteUser(List<Integer> ids) throws HibernateException {
        Integer result = userDao.delete(ids);
        return result;

    }

    @Override
    public UserDTO findEqualnamelogin(String value) {
        return null;
    }

    @Override
    public UserDTO findEqualUnique(String property, Object value) {
         UserEntity entity = userDao.findEqualUnique(property,value);
         UserDTO userDTO = UserConverter.entityToDto(entity);

        return userDTO;

    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        return null;
    }

    @Override
    public CheckLogin checkLogin(String namelogin) {
        CheckLogin checkLogin = new CheckLogin();
        String roleName=null;
        if (namelogin!=null ){
            Object[] objects = userDao.checkLogin(namelogin);
            checkLogin.setMemberExist((Boolean) objects[0]);
        }
        return checkLogin;
    }

    @Override
    public List<UserDTO> listUserOnline() {
        Object[] objects= userDao.listUserOnline();
        List<UserDTO> list = new ArrayList<>();
        for(UserEntity userEntity: (List<UserEntity>) objects[0]){
            UserDTO userDTO = UserConverter.entityToDto(userEntity);
            list.add(userDTO);
        }

        return list;
    }


}

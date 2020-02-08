package com.hieuminh.repository;

import com.hieuminh.entity.UserEntity;
import org.springframework.stereotype.Repository;

public interface UserDao extends  GenericDao<Integer,UserEntity>{
    Object[] checkLogin(String NameLogin );
    Object[] listUserOnline();
}

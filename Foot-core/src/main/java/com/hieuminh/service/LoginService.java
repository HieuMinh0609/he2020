package com.hieuminh.service;

import com.hieuminh.dto.LoginDTO;
import com.hieuminh.repository.LoginDao;
import org.hibernate.HibernateException;

import java.util.List;

public interface LoginService {
    void saveLogin(LoginDTO loginDTO);
    LoginDTO findByUserName(String property,Object value);
    LoginDTO findByIdUser(String property, Object value);
    LoginDTO updateLogin(LoginDTO loginDTO);
    Integer deleteLogin(List<Integer> ids) throws HibernateException;
    LoginDTO resetPassword(String email);
}

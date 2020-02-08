package com.hieuminh.service;

import java.util.List;
import java.util.Map;


import com.hieuminh.dto.CheckLogin;
import com.hieuminh.dto.UserDTO;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;


public interface UserService{
	Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
	Object[] findByPropertyMapNotLike(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);

	UserDTO findById(Integer idUser);
	void SaveUser(UserDTO userDTO);
	UserDTO updateUser(UserDTO userDTO);
	//CheckLogin checkLogin(String namelogin, String password);
	Integer deleteUser(List<Integer> ids) throws HibernateException;
	UserDTO findEqualnamelogin(String value);
	UserDTO findEqualUnique(String property, Object value);
	UserDTO findOneByUserName(String userName);
	CheckLogin checkLogin(String nameLogin);
	List<UserDTO> listUserOnline();

}

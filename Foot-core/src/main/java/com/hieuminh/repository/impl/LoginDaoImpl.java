package com.hieuminh.repository.impl;

import com.hieuminh.entity.LoginEntity;
import com.hieuminh.repository.LoginDao;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl extends AbstractDao<Integer, LoginEntity> implements LoginDao {
}

package com.hieuminh.repository.impl;

import com.hieuminh.entity.RoleEntity;
import com.hieuminh.repository.RoleDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {
}

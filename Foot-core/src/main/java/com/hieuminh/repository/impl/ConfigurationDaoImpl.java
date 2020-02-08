package com.hieuminh.repository.impl;

import com.hieuminh.entity.CommentEntity;
import com.hieuminh.entity.ConfigurationEntity;
import com.hieuminh.repository.CommentDao;
import com.hieuminh.repository.ConfigurationDao;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigurationDaoImpl  extends AbstractDao<Integer, ConfigurationEntity> implements ConfigurationDao {
}

package com.hieuminh.repository;

import com.hieuminh.dto.CommentDTO;
import com.hieuminh.entity.CommentEntity;

import java.util.Map;

public interface CommentDao  extends  GenericDao<Integer, CommentEntity>{
    Integer getCountComment(String id);
    Object[] findByPropertyAs(Integer id);

}

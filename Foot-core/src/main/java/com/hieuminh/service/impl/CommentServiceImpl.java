package com.hieuminh.service.impl;

import com.hieuminh.converter.CommentConverter;
import com.hieuminh.dto.CommentDTO;
import com.hieuminh.entity.CommentEntity;
import com.hieuminh.repository.CommentDao;
import com.hieuminh.service.CommentService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;


    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = commentDao.findByProperty(property, sortExpression, sortDirection, offset, limit);
        List<CommentDTO> list = new ArrayList<>();
        for (CommentEntity billEntity : (List<CommentEntity>) objects[1]) {
            CommentDTO commentDTO = CommentConverter.entityToDto(billEntity);
            list.add(commentDTO);
        }
        objects[1] = list;
        return objects;
    }

    @Override
    public Integer getCountComment(String id) {

      return  commentDao.getCountComment(id);

    }

    @Override
    public Object[] findByPropertyNotLike(Map<String, Object> property) {
        Object[] objects = commentDao.findByPropertyNotLike(property);
        List<CommentDTO> list = new ArrayList<>();
        for (CommentEntity billEntity : (List<CommentEntity>) objects[0]) {
            CommentDTO commentDTO = CommentConverter.entityToDto(billEntity);
            list.add(commentDTO);
        }
        objects[0] = list;
        return objects;



    }

    @Override
    public List<CommentDTO> findByPropertyAs(Integer id) {
        List<CommentDTO> list = new ArrayList<>();
        Object[] objects = commentDao.findByPropertyAs(id);


            for (CommentEntity billEntity : (List<CommentEntity>) objects[0]) {
                CommentDTO commentDTO = CommentConverter.entityToDto(billEntity);
                list.add(commentDTO);
            }


        return list;
    }

    @Override
    public CommentDTO findById(Integer id) {
        CommentEntity entity = commentDao.findById(id);
        CommentDTO dto = CommentConverter.entityToDto(entity);
        return dto;

    }

    @Override
    public void saveComment(CommentDTO commentDTO) {

        CommentEntity entity = CommentConverter.dtoToEntity(commentDTO);
        commentDao.save(entity);
    }

    @Override
    public Integer deleteComment(List<Integer> ids) throws HibernateException {
        Integer result = commentDao.delete(ids);
        return result;
    }
}

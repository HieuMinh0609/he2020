package com.hieuminh.service;

import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.CommentDTO;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface CommentService {
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Integer getCountComment(String id);
    Object[] findByPropertyNotLike(Map<String, Object> property);
    List<CommentDTO> findByPropertyAs(Integer id);
    CommentDTO findById(Integer id);
    void saveComment(CommentDTO commentDTO);
    Integer deleteComment(List<Integer> ids) throws HibernateException;
}

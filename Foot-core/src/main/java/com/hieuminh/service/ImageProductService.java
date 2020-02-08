package com.hieuminh.service;

import com.hieuminh.dto.ImageProductDTO;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface ImageProductService {
    ImageProductDTO  findEqualUnique(String property, Object value);
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    List<ImageProductDTO> findListById(String property, Object value);
    Integer deleteProduct(List<Integer> ids) throws HibernateException;
    void saveImageProduct(ImageProductDTO userDTO);
    void deleteImageList(String property,Integer value);
}

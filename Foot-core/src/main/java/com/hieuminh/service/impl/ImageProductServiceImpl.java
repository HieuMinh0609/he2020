package com.hieuminh.service.impl;

import com.hieuminh.converter.ImageProductConverter;
import com.hieuminh.dto.ImageProductDTO;
import com.hieuminh.entity.ImageProductEntity;
import com.hieuminh.repository.ImageProductDao;
import com.hieuminh.service.ImageProductService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ImageProductService")
@Transactional
 public class ImageProductServiceImpl implements ImageProductService {
    @Autowired
    private ImageProductDao imageProductDao;


    @Override
    public ImageProductDTO findEqualUnique(String property, Object value) {
        return null;
    }

    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        return new Object[0];
    }

    @Override
    public List<ImageProductDTO> findListById(String property, Object value) {
        List<ImageProductEntity> entity = imageProductDao.findListById(property,value);
        List<ImageProductDTO>  imageProductDTOS = new ArrayList<>();
        for(ImageProductEntity item:entity){
            imageProductDTOS.add(ImageProductConverter.entityToDto(item));
        }
        return  imageProductDTOS;
    }

    @Override
    public Integer deleteProduct(List<Integer> ids) throws HibernateException {
        Integer result = imageProductDao.delete(ids);
        return result;
    }

    @Override
    public void saveImageProduct(ImageProductDTO imageProductDTO) {
        ImageProductEntity entity = ImageProductConverter.dtoToEntity(imageProductDTO);
        imageProductDao.save(entity);
    }

    @Override
    public void deleteImageList(String property, Integer value) {
        imageProductDao.deleteListImage(property,value);
    }


}

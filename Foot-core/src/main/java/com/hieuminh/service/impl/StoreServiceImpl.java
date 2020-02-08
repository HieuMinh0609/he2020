package com.hieuminh.service.impl;

import com.hieuminh.converter.StoreConverter;
import com.hieuminh.converter.TypeConverter;
import com.hieuminh.dto.StoreDTO;
import com.hieuminh.dto.TypeDTO;
import com.hieuminh.entity.StoreEntity;
import com.hieuminh.entity.TypeEntity;
import com.hieuminh.repository.StoreDao;
import com.hieuminh.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("StoreService")
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDao storeDao;

    @Override
    public List<StoreDTO> findAll() {
        List<StoreEntity> entity = storeDao.findAll() ;
        List<StoreDTO>  storeDTOS = new ArrayList<>();
        for(StoreEntity item:entity){
            storeDTOS.add(StoreConverter.entityToDto(item));
        }
        return  storeDTOS;

    }

    @Override
    public StoreDTO findById(Integer id) {
        StoreEntity entity = storeDao.findById(id) ;
        StoreDTO dto = StoreConverter.entityToDto(entity);
        return  dto;
    }
}

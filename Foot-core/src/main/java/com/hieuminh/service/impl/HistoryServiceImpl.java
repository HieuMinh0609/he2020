package com.hieuminh.service.impl;

import com.hieuminh.converter.HistoryConverter;
import com.hieuminh.dto.HistoryDTO;
import com.hieuminh.entity.MyHistoryEntity;
import com.hieuminh.repository.HistoryDao;
import com.hieuminh.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("HistoryService")
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryDao historyDao;


    @Override
    public void SaveHistory(HistoryDTO historyDTO) {

        MyHistoryEntity entity = HistoryConverter.dtoToEntity(historyDTO);
        historyDao.save(entity);
    }

    @Override
    public HistoryDTO findById(Integer id) {


        MyHistoryEntity entity = historyDao.findById(id) ;
        HistoryDTO dto = HistoryConverter.entityToDto(entity);
        return  dto;
    }

    @Override
    public Object[] findByHistory(Integer offset, Integer limit) {
        Object[] objects= historyDao.findByHistory(offset,limit);
        List<HistoryDTO> list = new ArrayList<>();
        for(MyHistoryEntity historyEntity: (List<MyHistoryEntity>) objects[0]){
            HistoryDTO historyDTO = HistoryConverter.entityToDto(historyEntity);
            list.add(historyDTO);
        }
        objects[0]=list;
        return objects;
    }

    @Override
    public List<HistoryDTO> findAll() {

       List<MyHistoryEntity> historyEntities= historyDao.findAll();
        List<HistoryDTO> list = new ArrayList<>();
        for(MyHistoryEntity myHistoryEntity:  historyEntities){
            HistoryDTO historyDTO = HistoryConverter.entityToDto(myHistoryEntity);
            list.add(historyDTO);
        }

        return list;

    }
}

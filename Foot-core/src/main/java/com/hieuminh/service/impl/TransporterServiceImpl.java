package com.hieuminh.service.impl;

import com.hieuminh.converter.TransporterConverter;
import com.hieuminh.converter.UserConverter;
import com.hieuminh.dto.TransporterDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.entity.TransporterEntity;
import com.hieuminh.entity.UserEntity;
import com.hieuminh.repository.TransporterDao;
import com.hieuminh.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("TransporterService")
public class TransporterServiceImpl implements TransporterService {
    @Autowired
    TransporterDao transporterDao;

    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= transporterDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<TransporterDTO> list = new ArrayList<TransporterDTO>();
        for(TransporterEntity transporterEntity: (List<TransporterEntity>) objects[1]){
            TransporterDTO transporterDTO = TransporterConverter.entityToDto(transporterEntity);
            list.add(transporterDTO);
        }
        objects[1]=list;
        return objects;
}

    @Override
    public Object[] findByPropertyMapNotLike(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= transporterDao.findByPropertyMapNotLike(property,sortExpression,sortDirection,offset,limit);
        List<TransporterDTO> list = new ArrayList<TransporterDTO>();
        for(TransporterEntity transporterEntity: (List<TransporterEntity>) objects[1]){
            TransporterDTO transporterDTO = TransporterConverter.entityToDto(transporterEntity);
            list.add(transporterDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public List<Integer> getListIdBillOnDayByIdUser(Integer id,String day,String month,String year,String status) {

        List<Integer> list = transporterDao.getListIdBillOnDayByIdUser(id,day,month,year,status);

        return list;

    }

    @Override
    public void SaveTransporter(TransporterDTO transporterDTO) {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());

        transporterDTO.setTimeStart(createDate);
        transporterDTO.setStatus(0);
        TransporterEntity entity = TransporterConverter.dtoToEntity(transporterDTO);
        transporterDao.save(entity);
    }

    @Override
    public TransporterDTO findEqualUnique(String property, Object value) {
        TransporterEntity entity = transporterDao.findEqualUnique(property,value);
        TransporterDTO transporterDTO = TransporterConverter.entityToDto(entity);

        return transporterDTO;
    }

    @Override
    public TransporterDTO updateTransporter(TransporterDTO transporterDTO) {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());

        transporterDTO.setTimeEnd(createDate);
        TransporterEntity transporterEntity = TransporterConverter.dtoToEntity(transporterDTO);
        transporterEntity =  transporterDao.update(transporterEntity);
        transporterDTO  = TransporterConverter.entityToDto(transporterEntity);
        return transporterDTO;
    }
}

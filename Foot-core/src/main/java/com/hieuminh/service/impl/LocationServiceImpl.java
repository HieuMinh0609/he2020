package com.hieuminh.service.impl;

import com.hieuminh.converter.LocationConverter;
import com.hieuminh.converter.LoginConverter;
import com.hieuminh.dto.LocationDTO;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.entity.LocationEntity;
import com.hieuminh.entity.LoginEntity;
import com.hieuminh.repository.LocationDao;
import com.hieuminh.service.LocationService;
import com.hieuminh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("LocationService")
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;

    @Override
    public LocationDTO updateLocation(LocationDTO locationDTO) {
        LocationEntity locationEntity = LocationConverter.dtoToEntity(locationDTO);
        locationEntity = locationDao.update(locationEntity);
        locationDTO = LocationConverter.entityToDto(locationEntity);
        return locationDTO;
    }



    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= locationDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<LocationDTO> list = new ArrayList<>();
        for(LocationEntity locationEntity: (List<LocationEntity>) objects[1]){
            LocationDTO locationDTO = LocationConverter.entityToDto(locationEntity);
            list.add(locationDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public LocationDTO findEqualUnique(String property, Object value) {
        try{
            LocationEntity locationEntity = locationDao.findEqualUnique(property,value);
            LocationDTO locationDTO = LocationConverter.entityToDto(locationEntity);

            return locationDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Object[] findByPropertyMapNotLike(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= locationDao.findByPropertyMapNotLike(property,sortExpression,sortDirection,offset,limit);
        List<LocationDTO> list = new ArrayList<LocationDTO>();
        for(LocationEntity locationEntity: (List<LocationEntity>) objects[1]){
            LocationDTO locationDTO = LocationConverter.entityToDto(locationEntity);
            list.add(locationDTO);
        }
        objects[1]=list;
        return objects;
    }

}

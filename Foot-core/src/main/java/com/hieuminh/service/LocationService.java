package com.hieuminh.service;

import com.hieuminh.dto.LocationDTO;

import java.util.Map;

public interface LocationService {
    LocationDTO updateLocation(LocationDTO locationDTO);
     Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    LocationDTO findEqualUnique(String property, Object value);
    Object[] findByPropertyMapNotLike(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);


}

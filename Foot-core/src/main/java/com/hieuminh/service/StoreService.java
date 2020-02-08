package com.hieuminh.service;

import com.hieuminh.dto.StoreDTO;
import com.hieuminh.dto.TypeDTO;

import java.util.List;

public interface StoreService {
    List<StoreDTO> findAll();
    StoreDTO findById(Integer id);
}

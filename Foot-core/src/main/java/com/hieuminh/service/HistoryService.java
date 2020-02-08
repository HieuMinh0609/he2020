package com.hieuminh.service;

import com.hieuminh.dto.HistoryDTO;

import java.util.List;

public interface HistoryService {
    void SaveHistory(HistoryDTO historyDTO);
    HistoryDTO findById(Integer id);
    Object[] findByHistory(Integer offset, Integer limit);
    List<HistoryDTO> findAll();

}

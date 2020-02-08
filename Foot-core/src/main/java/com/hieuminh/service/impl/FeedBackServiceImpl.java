package com.hieuminh.service.impl;

import com.hieuminh.converter.FeedBackConverter;
import com.hieuminh.dto.FeedBackDTO;
import com.hieuminh.entity.FeedBackEntity;
import com.hieuminh.repository.FeedBackDao;
import com.hieuminh.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("FeedBackService")
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    FeedBackDao feedBackDao;


    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= feedBackDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<FeedBackDTO> list = new ArrayList<>();
        for(FeedBackEntity feedBackEntity: (List<FeedBackEntity>) objects[1]){
            FeedBackDTO feedBackDTO = FeedBackConverter.entityToDto(feedBackEntity);
            list.add(feedBackDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public void UpdateFeedBack() {
        feedBackDao.updateFeedBack();
    }
}

package com.hieuminh.repository;

import com.hieuminh.entity.FeedBackEntity;

public interface FeedBackDao extends  GenericDao<Integer, FeedBackEntity> {
    void updateFeedBack();
}

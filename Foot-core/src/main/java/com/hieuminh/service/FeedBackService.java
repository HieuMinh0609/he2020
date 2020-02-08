package com.hieuminh.service;

import java.util.Map;

public interface FeedBackService {
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    void UpdateFeedBack();
}

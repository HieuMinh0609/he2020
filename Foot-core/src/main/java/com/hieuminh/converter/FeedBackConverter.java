package com.hieuminh.converter;

import com.hieuminh.dto.FeedBackDTO;
import com.hieuminh.entity.FeedBackEntity;

public class FeedBackConverter {



    public static FeedBackDTO entityToDto(FeedBackEntity feedBackEntity) {
        FeedBackDTO feedBackDTO = new FeedBackDTO();

        feedBackDTO.setIdFeedBack(feedBackEntity.getIdFeedBack());
        feedBackDTO.setIdUserFeedBack(UserConverter.entityToDto(feedBackEntity.getIdUserFeedBack()));
        feedBackDTO.setDateTime(feedBackEntity.getDateTime());
        feedBackDTO.setFeedBackContent(feedBackEntity.getFeedBackContent());
        feedBackDTO.setCheck(feedBackEntity.getCheck());

        return feedBackDTO;
}

    public static FeedBackEntity dtoToEntity(FeedBackDTO feedBackDTO) {
        FeedBackEntity feedBackEntity = new FeedBackEntity();

        feedBackEntity.setIdFeedBack(feedBackDTO.getIdFeedBack());
        feedBackEntity.setIdUserFeedBack(UserConverter.dtoToEntity(feedBackDTO.getIdUserFeedBack()));
        feedBackEntity.setDateTime(feedBackDTO.getDateTime());
        feedBackEntity.setFeedBackContent(feedBackDTO.getFeedBackContent());
        feedBackEntity.setCheck(feedBackDTO.getCheck());


        return feedBackEntity;
    }
}

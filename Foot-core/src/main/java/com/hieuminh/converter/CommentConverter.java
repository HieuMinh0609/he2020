package com.hieuminh.converter;

import com.hieuminh.dto.CommentDTO;
import com.hieuminh.entity.CommentEntity;

public class CommentConverter {

    public static CommentDTO entityToDto(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
            commentDTO.setContent(commentEntity.getContent());
            commentDTO.setIdComment(commentEntity.getIdComment());
            commentDTO.setDateTime(commentEntity.getDateTime());
            commentDTO.setIdProductEntity(ProductConverter.entityToDto(commentEntity.getIdProductEntity()));
            commentDTO.setIdUserEntity(UserConverter.entityToDto(commentEntity.getIdUserEntity()));
            commentDTO.setContent(commentEntity.getContent());
            commentDTO.setRate(commentEntity.getRate());
        return commentDTO;
    }
    public static CommentEntity dtoToEntity(CommentDTO commentDTO) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setIdComment(commentDTO.getIdComment());
        commentEntity.setDateTime(commentDTO.getDateTime());
        commentEntity.setIdProductEntity(ProductConverter.dtoToEntity(commentDTO.getIdProductEntity()));
        commentEntity.setIdUserEntity(UserConverter.dtoToEntity(commentDTO.getIdUserEntity()));
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setRate(commentDTO.getRate());
        return commentEntity;
    }
}

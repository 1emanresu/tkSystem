package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkClientPhoto;

public interface TkClientPhotoMapper {
    int deleteByPrimaryKey(String tkClientPhotoId);

    int insert(TkClientPhoto record);

    int insertSelective(TkClientPhoto record);

    TkClientPhoto selectByPrimaryKey(String tkClientPhotoId);

    int updateByPrimaryKeySelective(TkClientPhoto record);

    int updateByPrimaryKey(TkClientPhoto record);
}
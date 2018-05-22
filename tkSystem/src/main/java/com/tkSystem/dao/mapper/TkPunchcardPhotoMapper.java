package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkPunchcardPhoto;

public interface TkPunchcardPhotoMapper {
    int deleteByPrimaryKey(String tkPunchcardPhotoId);

    int insert(TkPunchcardPhoto record);

    int insertSelective(TkPunchcardPhoto record);

    TkPunchcardPhoto selectByPrimaryKey(String tkPunchcardPhotoId);

    int updateByPrimaryKeySelective(TkPunchcardPhoto record);

    int updateByPrimaryKey(TkPunchcardPhoto record);
}
package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkPlanPhoto;

public interface TkPlanPhotoMapper {
    int deleteByPrimaryKey(String tkPlanPhotoId);

    int insert(TkPlanPhoto record);

    int insertSelective(TkPlanPhoto record);

    TkPlanPhoto selectByPrimaryKey(String tkPlanPhotoId);

    int updateByPrimaryKeySelective(TkPlanPhoto record);

    int updateByPrimaryKey(TkPlanPhoto record);
}
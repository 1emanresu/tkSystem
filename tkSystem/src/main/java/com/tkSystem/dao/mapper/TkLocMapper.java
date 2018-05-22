package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkLoc;

public interface TkLocMapper {
    int deleteByPrimaryKey(String tkLocId);

    int insert(TkLoc record);

    int insertSelective(TkLoc record);

    TkLoc selectByPrimaryKey(String tkLocId);

    int updateByPrimaryKeySelective(TkLoc record);

    int updateByPrimaryKey(TkLoc record);
}
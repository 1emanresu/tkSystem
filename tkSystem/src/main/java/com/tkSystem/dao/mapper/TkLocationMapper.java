package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkLocation;

public interface TkLocationMapper {
    int deleteByPrimaryKey(String tkLocationId);

    int insert(TkLocation record);

    int insertSelective(TkLocation record);

    TkLocation selectByPrimaryKey(String tkLocationId);

    int updateByPrimaryKeySelective(TkLocation record);

    int updateByPrimaryKey(TkLocation record);
}
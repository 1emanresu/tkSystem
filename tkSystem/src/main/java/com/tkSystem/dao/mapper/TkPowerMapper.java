package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkPower;

public interface TkPowerMapper {
    int deleteByPrimaryKey(String tkPowerId);

    int insert(TkPower record);

    int insertSelective(TkPower record);

    TkPower selectByPrimaryKey(String tkPowerId);

    int updateByPrimaryKeySelective(TkPower record);

    int updateByPrimaryKey(TkPower record);
}
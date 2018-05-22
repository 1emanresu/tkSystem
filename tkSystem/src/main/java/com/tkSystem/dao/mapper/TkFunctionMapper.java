package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkFunction;

public interface TkFunctionMapper {
    int deleteByPrimaryKey(String tkFunctionId);

    int insert(TkFunction record);

    int insertSelective(TkFunction record);

    TkFunction selectByPrimaryKey(String tkFunctionId);

    int updateByPrimaryKeySelective(TkFunction record);

    int updateByPrimaryKey(TkFunction record);
}
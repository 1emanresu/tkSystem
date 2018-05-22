package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkClientType;

public interface TkClientTypeMapper {
    int deleteByPrimaryKey(String tkTypeId);

    int insert(TkClientType record);

    int insertSelective(TkClientType record);

    TkClientType selectByPrimaryKey(String tkTypeId);

    int updateByPrimaryKeySelective(TkClientType record);

    int updateByPrimaryKey(TkClientType record);
}
package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkGoodType;

public interface TkGoodTypeMapper {
    int deleteByPrimaryKey(String tkGoodTypeId);

    int insert(TkGoodType record);

    int insertSelective(TkGoodType record);

    TkGoodType selectByPrimaryKey(String tkGoodTypeId);

    int updateByPrimaryKeySelective(TkGoodType record);

    int updateByPrimaryKey(TkGoodType record);
}
package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkGood;
import com.tkSystem.tools.WyMap;

public interface TkGoodMapper {
    int deleteByPrimaryKey(WyMap tkGoodId);

    int insert(WyMap record);

    int insertSelective(WyMap record);

    TkGood selectByPrimaryKey(WyMap tkGoodId);

    int updateByPrimaryKeySelective(WyMap record);

    int updateByPrimaryKey(WyMap record);
}
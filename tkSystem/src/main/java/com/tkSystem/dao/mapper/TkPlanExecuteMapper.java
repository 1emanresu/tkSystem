package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkPlanExecute;
import com.tkSystem.tools.WyMap;

public interface TkPlanExecuteMapper {
    int deleteByPrimaryKey(WyMap tkPlanExecuteId);

    int insert(WyMap record);

    int insertSelective(WyMap record);

    TkPlanExecute selectByPrimaryKey(WyMap tkPlanExecuteId);

    int updateByPrimaryKeySelective(WyMap record);

    int updateByPrimaryKey(WyMap record);
}
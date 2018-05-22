package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkPlanExecuteClient;
import com.tkSystem.tools.WyMap;

public interface TkPlanExecuteClientMapper {
    int deleteByPrimaryKey(WyMap tkPlanExecuteClientId);

    int insert(WyMap record);

    int insertSelective(WyMap record);

    TkPlanExecuteClient selectByPrimaryKey(WyMap tkPlanExecuteClientId);

    int updateByPrimaryKeySelective(WyMap record);

    int updateByPrimaryKey(WyMap record);
}
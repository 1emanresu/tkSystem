package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkPlanExecute;
import com.tkSystem.tools.WyMap;

public interface TkPlanExecuteMapper {
    int deleteByPrimaryKey(WyMap tkPlanExecuteId);

    int insert(WyMap record);

    int insertSelective(WyMap record);

    TkPlanExecute selectByPrimaryKey(WyMap tkPlanExecuteId);
    //获取自己被分配到的任务
    List<TkPlanExecute> selectByUserId(String UserId);

    int updateByPrimaryKeySelective(WyMap record);

    int updateByPrimaryKey(WyMap record);
}
package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkPlanDetail;
import com.tkSystem.tools.WyMap;

public interface TkPlanDetailMapper {
	int insert(WyMap paMap);

	int insertSelective(WyMap paMap);

	List<WyMap> getTodayTask(WyMap paMap);// 获取拓客人员今日任务

	WyMap getTargetAchieve(WyMap paMap);// 获取拓客人员今日任务

	int updateTkPlanState(WyMap paMap);

	int updateTkUserId(WyMap paMap);

	int updateTargetAchieve(WyMap paMap);

	List<WyMap> getTaskToClock(WyMap paMap);

	List<WyMap> getIdByUserId(WyMap paMap);
	List<WyMap> getAll( );

	WyMap getPlanDetailByPlanId(WyMap wyMap);

	WyMap getPlanNameByPlanId(WyMap wyMap);

	List<WyMap> getPlanByTkUserId(WyMap paMap);
	
	int updateStartDate(WyMap paMap);

}
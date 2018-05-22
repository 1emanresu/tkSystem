package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkPlan;
import com.tkSystem.tools.WyMap;

public interface TkPlanMapper {
    int deleteByPrimaryKey(String tkPlanId);

	int insert(WyMap wyMap);

    int insertSelective(WyMap wyMap);
    int postPlanExecute(WyMap wyMap);
     
    TkPlan selectByPrimaryKey(WyMap wyMap);
   
    List<WyMap> selectAll();
    List<WyMap> getPlanThree(WyMap wyMap);
    List<WyMap> getPlanThreeH(WyMap wyMap);
    
    List<WyMap>   selectByUserId(WyMap wyMap);
    
    List<WyMap>   getPlanNameByUserId(WyMap wyMap);
    
    int updateByPrimaryKeySelective(WyMap wyMap);

    int updateByPrimaryKey(WyMap wyMap);

    List<WyMap> getAreadyPlan(WyMap wyMap);

	int updateStartDate(WyMap plan);
    
}
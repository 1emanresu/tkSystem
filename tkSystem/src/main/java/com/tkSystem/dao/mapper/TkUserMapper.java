package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.tools.WyMap;

public interface TkUserMapper {
	int deleteByPrimaryKey(WyMap tkUserId);

	int insert(WyMap record);

	int insertSelective(WyMap record);
	TkUser selectByPrimaryKey(WyMap tkUserId);
	List<WyMap> selectAll();
	List<WyMap> getEmployee(WyMap paMap );
	List<WyMap> getClientAmount(WyMap paMap );
	List<WyMap> getEmployeeByName(WyMap tkUserId );
	
	int updateByPrimaryKeySelective(WyMap record);

	int updateByPrimaryKey(WyMap record);

	int updateByPasswordSelective(WyMap record);
	
	TkUser userlogin(WyMap record);

	/**
	 * token吊销
	 * 
	 * @param record
	 * @return
	 */
	int distroyToken(WyMap record);

	TkUser wechatLogin(WyMap paMap);

	List<WyMap> getTeamIndex(WyMap paMap);

	List<WyMap> selectBySelective(WyMap wyMap1);

}
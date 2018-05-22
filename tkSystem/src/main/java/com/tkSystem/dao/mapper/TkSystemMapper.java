package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkApply;
import com.tkSystem.tools.WyMap;

public interface TkSystemMapper {
	List<WyMap> selectByUserId(WyMap WyMap);

	int insert(WyMap WyMap);

	int delete(WyMap WyMap);

	List<WyMap> getCompanyIndex(WyMap paMap);

	List<WyMap> getWorkNumber(WyMap paMap);

	List<WyMap> getTeamIndex(WyMap paMap);
	List<WyMap> getClientInfoByPlanId(WyMap paMap);
	List<WyMap> getTkUserByPlanId(WyMap paMap);
	List<WyMap> getTkChannel(WyMap paMap);
	List<WyMap> selectFreshdate(WyMap paMap);
	
	int postClientInfo(WyMap paMap);

	int postClientGoodsInfo(WyMap paMap1);
	List<WyMap> getNumGroupChannelByUserId(WyMap paMap);
	int updateClienType(WyMap paMap);

	int putClientInfo(WyMap paMap);
}
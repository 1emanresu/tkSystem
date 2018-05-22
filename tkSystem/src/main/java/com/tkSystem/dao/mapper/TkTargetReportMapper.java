package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkTargetReport;
import com.tkSystem.tools.WyMap;

public interface TkTargetReportMapper {
	int deleteByPrimaryKey(WyMap tkTargetReportId);

	int insert(WyMap record);

	int insertSelective(WyMap record);

	TkTargetReport selectByPrimaryKey(WyMap tkTargetReportId);

	List<WyMap> selectByUserId(WyMap tkTargetReportId);
	List<WyMap> selectByReUserId(WyMap tkTargetReportId);
	
	int updateByPrimaryKeySelective(WyMap record);

	int updateByPrimaryKey(WyMap record);
	
	WyMap getReportByManageDetail(WyMap tkTargetReportId);

	int putReportByManage(WyMap mapP);

}
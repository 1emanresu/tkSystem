package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkUserGrade;
import com.tkSystem.tools.WyMap;

public interface TkUserGradeMapper {
	int deleteByPrimaryKey(String tkUserGradeId);

	int insert(TkUserGrade record);

	int insertSelective(TkUserGrade record);

	TkUserGrade selectByPrimaryKey(String tkUserGradeId);
	
	List<WyMap>  selectBySelective(WyMap WyMap);
	
	List<TkUserGrade> selectAll();

	int updateByPrimaryKeySelective(TkUserGrade record);

	int updateByPrimaryKey(TkUserGrade record);

	int updateState(TkUserGrade record);

	int updateGradePid(WyMap record);

}
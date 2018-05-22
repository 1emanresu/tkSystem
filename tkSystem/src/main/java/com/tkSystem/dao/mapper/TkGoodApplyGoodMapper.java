package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkGoodApplyGood;
import com.tkSystem.tools.WyMap;

public interface TkGoodApplyGoodMapper {
	int deleteByPrimaryKey(String tkGoodApplyId);

	int insert(TkGoodApplyGood record);

	int insertSelective(TkGoodApplyGood record);

	int postApplicationMaterials(WyMap record);

	TkGoodApplyGood selectByPrimaryKey(String tkGoodApplyId);

	List<TkGoodApplyGood> selectAll();

	List<WyMap> selectAllByTkUserId(WyMap record);

	List<WyMap> selectBySelective(WyMap record);

	WyMap selectGoodApplyId(WyMap record);

	int updateByPrimaryKeySelective(TkGoodApplyGood record);

	int updateBySelective(WyMap record);

	int updateByPrimaryKey(TkGoodApplyGood record);
}
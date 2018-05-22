package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkApply;

public interface TkApplyMapper {
    int deleteByPrimaryKey(String tkApplyId);

    int insert(TkApply record);

    int insertSelective(TkApply record);

    TkApply selectByPrimaryKey(String tkApplyId);

    int updateByPrimaryKeySelective(TkApply record);

    int updateByPrimaryKey(TkApply record);
}
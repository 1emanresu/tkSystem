package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkLoginHistory;

public interface TkLoginHistoryMapper {
    int deleteByPrimaryKey(String tkLoginHistoryId);

    int insert(TkLoginHistory record);

    int insertSelective(TkLoginHistory record);

    TkLoginHistory selectByPrimaryKey(String tkLoginHistoryId);

    int updateByPrimaryKeySelective(TkLoginHistory record);

    int updateByPrimaryKey(TkLoginHistory record);
}
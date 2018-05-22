package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkChannel;

public interface TkChannelMapper {
    int deleteByPrimaryKey(String tkChannelId);

    int insert(TkChannel record);

    int insertSelective(TkChannel record);

    TkChannel selectByPrimaryKey(String tkChannelId);

    int updateByPrimaryKeySelective(TkChannel record);

    int updateByPrimaryKey(TkChannel record);
}
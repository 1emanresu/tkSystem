package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.WechatUser;

public interface WechatUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    WechatUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);
}
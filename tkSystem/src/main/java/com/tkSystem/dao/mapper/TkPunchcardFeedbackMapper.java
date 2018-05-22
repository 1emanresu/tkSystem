package com.tkSystem.dao.mapper;

import com.tkSystem.dao.entity.TkPunchcardFeedback;

public interface TkPunchcardFeedbackMapper {
    int deleteByPrimaryKey(String tkPunchcardFeedbackId);

    int insert(TkPunchcardFeedback record);

    int insertSelective(TkPunchcardFeedback record);

    TkPunchcardFeedback selectByPrimaryKey(String tkPunchcardFeedbackId);

    int updateByPrimaryKeySelective(TkPunchcardFeedback record);

    int updateByPrimaryKey(TkPunchcardFeedback record);
}
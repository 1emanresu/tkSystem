package com.tkSystem.dao.mapper;

import java.util.List;

import com.tkSystem.dao.entity.TkPunchcard;
import com.tkSystem.tools.WyMap;

public interface TkPunchcardMapper {
    int deleteByPrimaryKey(String tkPunchcardId);

    int insert(TkPunchcard record);

    int insertSelective(TkPunchcard record);

    TkPunchcard selectByPrimaryKey(String tkPunchcardId);

    int updateByPrimaryKeySelective(TkPunchcard record);

    int updateByPrimaryKey(TkPunchcard record);
    
    List<WyMap> getClockInRecord(WyMap wyMap);//获取打卡记录
   

    
}
package com.tkSystem.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tkSystem.dao.entity.TkUserGrade;
import com.tkSystem.dao.mapper.TkUserGradeMapper;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.WyMap;

@Service
public class PersonnelManagementServier {
	
	@Resource
	TkUserGradeMapper tkUserGradeMapper;
	/**
	 * 获取所有拓客人员
	 * */
	public RetCode getPersonneInfo(WyMap wyMap){//获取所有拓客人员
		RetCode retCode=RetCode.getErrorCode();
		try {
			retCode=RetCode.getSuccessCode(tkUserGradeMapper.selectAll());
		}catch (Exception e) {
		}
		return retCode;
	}
	/**
	 * 删除拓客人员，删除时，同时解除上下级关系
	 * */
	public RetCode delPersonneInfo(WyMap wyMap){//删除拓客人员
		String tkUserGradeId,tkUserState="";
		RetCode retCode = RetCode.getErrorCode();
		try {
			tkUserGradeId=wyMap.get("tkUserGradeId").toString().trim();
			tkUserState=wyMap.get("tkUserState").toString().trim();
			
			TkUserGrade TkUserGrade=new TkUserGrade();
			TkUserGrade.setTkUserGradeId(tkUserGradeId);
			TkUserGrade.setTkUserState(tkUserState);
			retCode=RetCode.getSuccessCode(tkUserGradeMapper.updateState(TkUserGrade));
		}catch (Exception e) {
		}
		return retCode;
	}
}

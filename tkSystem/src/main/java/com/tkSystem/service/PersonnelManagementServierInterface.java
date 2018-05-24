package com.tkSystem.service;

import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.WyMap;

public interface PersonnelManagementServierInterface {

	/**
	 * 获取所有拓客人员
	 * */
	RetCode getPersonneInfo(WyMap wyMap);

	/**
	 * 删除拓客人员，删除时，同时解除上下级关系
	 * */
	RetCode delPersonneInfo(WyMap wyMap);

}
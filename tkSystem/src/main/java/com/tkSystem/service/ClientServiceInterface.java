package com.tkSystem.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tkSystem.tools.RetCode;

public interface ClientServiceInterface {

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#getClientInfo(javax.servlet.http.HttpServletRequest)
	 */
	RetCode getClientInfo(HttpServletRequest request);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#getWorkNumber(javax.servlet.http.HttpServletRequest)
	 */
	RetCode getWorkNumber(HttpServletRequest request);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#getTeamIndex(javax.servlet.http.HttpServletRequest)
	 */
	RetCode getTeamIndex(HttpServletRequest request);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#getClientInfoByPlanId(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	RetCode getClientInfoByPlanId(HttpServletRequest request, HttpServletResponse response);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#getCompanyIndex(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	RetCode getCompanyIndex(HttpServletRequest request, HttpServletResponse response);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#getTkChannel(javax.servlet.http.HttpServletRequest)
	 */
	RetCode getTkChannel(HttpServletRequest request);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#postClientInfo(javax.servlet.http.HttpServletRequest)
	 */
	RetCode postClientInfo(HttpServletRequest request);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#getClientNumByChannel(javax.servlet.http.HttpServletRequest)
	 */
	RetCode getClientNumByChannel(HttpServletRequest request);

	/* (non-Javadoc)
	 * @see com.tkSystem.service.vcxv#putClientInfo(javax.servlet.http.HttpServletRequest)
	 */
	Object putClientInfo(HttpServletRequest request);

}
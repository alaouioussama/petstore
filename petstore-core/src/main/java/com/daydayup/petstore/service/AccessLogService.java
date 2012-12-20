package com.daydayup.petstore.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daydayup.petstore.dao.AccessLogMapper;
import com.daydayup.petstore.entity.AccessLog;

public class AccessLogService {
	private AccessLogMapper dao;

	public AccessLogMapper getDao() {
		return dao;
	}

	public void setDao(AccessLogMapper dao) {
		this.dao = dao;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void add(String page, String sessionId, String ip, String ua) {
		AccessLog log = new AccessLog();
		log.setPage(page);
		log.setSessionId(sessionId);
		log.setIp(ip);
		log.setUa(ua);
		dao.add(log);
	}
}

package com.daydayup.petstore.service;

import java.security.PrivilegedAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.druid.wall.WallProvider;
import com.daydayup.petstore.dao.DatabaseInitMapper;

public class DatabaseInitService implements InitializingBean {
	private final static Log LOG = LogFactory.getLog(DatabaseInitService.class);

	private DatabaseInitMapper dao;

	public DatabaseInitMapper getDao() {
		return dao;
	}

	public void setDao(DatabaseInitMapper dao) {
		this.dao = dao;
	}

	public void createAccessLogTable() {
		if (LOG.isInfoEnabled()) {
			LOG.info("create accesslog 'user'");
		}
		WallProvider.doPrivileged(new PrivilegedAction<Object>() {

			@Override
			public Object run() {
				dao.createVisitLogTable();
				return null;
			}
		});
	}

	public void createUserTable() {
		if (LOG.isInfoEnabled()) {
			LOG.info("create table 'user'");
		}
		WallProvider.doPrivileged(new PrivilegedAction<Object>() {

			@Override
			public Object run() {
				dao.createUserTable();
				return null;
			}
		});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		createAccessLogTable();
		createUserTable();

	}
}

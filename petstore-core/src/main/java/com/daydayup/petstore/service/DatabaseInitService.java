package com.daydayup.petstore.service;

import java.security.PrivilegedAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.wall.WallProvider;
import com.daydayup.petstore.dao.DatabaseInitMapper;
import com.daydayup.petstore.entity.User;

public class DatabaseInitService implements InitializingBean {
	private final static Log LOG = LogFactory.getLog(DatabaseInitService.class);

	private DatabaseInitMapper dao;

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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
		
		User user = new User();
		user.setName("admin");
		user.setPassword("admin");
		userService.addUser(user);
	}
}

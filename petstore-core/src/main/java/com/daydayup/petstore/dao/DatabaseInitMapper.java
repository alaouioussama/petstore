package com.daydayup.petstore.dao;

import org.apache.ibatis.annotations.Update;

public interface DatabaseInitMapper {

	@Update("CREATE TABLE user (" + //
			"      name VARCHAR(64) NOT NULL PRIMARY KEY," + //
			"      password VARCHAR(128)," + //
			"      createTime DATETIME DEFAULT NOW()" + //
			")")
	void createUserTable();

	@Update("CREATE TABLE accesslog (" + //
			"      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," + //
			"      page varchar(256)," + //
			"      session_id varchar(128)," + //
			"      ua varchar(1024)," + //
			"      ip varchar(64)," + //
			"      createTime DATETIME DEFAULT NOW()" + //
			")")
	void createVisitLogTable();
	
}

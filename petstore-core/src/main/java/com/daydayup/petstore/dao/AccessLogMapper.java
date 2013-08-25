package com.daydayup.petstore.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.daydayup.petstore.entity.AccessLog;

public interface AccessLogMapper {

    @Insert("insert into accesslog (session_id, page, ua, ip, createTime) " + //
            "values (#{log.sessionId}, #{log.page}, #{log.ua}, #{log.ip}, now())" //
    )
    void add(@Param("log") AccessLog log);
}

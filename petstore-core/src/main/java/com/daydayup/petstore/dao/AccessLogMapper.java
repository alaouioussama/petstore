package com.daydayup.petstore.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.daydayup.petstore.entity.AccessLog;

public interface AccessLogMapper {

    @Insert("insert into user (page, session_id, page, ua, ip) " + //
            "values (#{log.page}, #{log.sessionId}, #{log.page}, #{log.ua}, #{log.ip})" //
    )
    void add(@Param("log") AccessLog log);
}

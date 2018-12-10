package com.layman.search.service.impl;

import com.layman.mapper.TExceptionLogMapper;
import com.layman.pojo.TExceptionLog;
import com.layman.search.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    @Autowired
    TExceptionLogMapper mapper;

    public void insertExceptionLogSelective(TExceptionLog log) {
        mapper.insert(log);
    }
}

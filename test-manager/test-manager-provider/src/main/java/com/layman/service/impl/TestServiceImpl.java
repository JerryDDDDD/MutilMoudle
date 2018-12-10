package com.layman.service.impl;

import com.layman.service.TestService;
import com.layman.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public String queryNow() {
        // 1.注入mapper
        // 2.调用mapper方法
        return testMapper.queryNow();
    }
}

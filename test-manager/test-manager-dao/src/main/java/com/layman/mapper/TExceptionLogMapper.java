package com.layman.mapper;

import com.layman.pojo.TExceptionLog;

import java.util.List;

public interface TExceptionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TExceptionLog record);

    TExceptionLog selectByPrimaryKey(Integer id);

    List<TExceptionLog> selectAll();

    int updateByPrimaryKey(TExceptionLog record);
}
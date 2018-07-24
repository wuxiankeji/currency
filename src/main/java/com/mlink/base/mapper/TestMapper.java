package com.mlink.base.mapper;

import com.mlink.base.entity.Test;
import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(String id);

    int insert(Test record);

    Test selectByPrimaryKey(String id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);
}
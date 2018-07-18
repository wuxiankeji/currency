package com.mlink.base.mapper;

import com.mlink.base.entity.RescueBankInfo;
import java.util.List;

public interface RescueBankInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(RescueBankInfo record);

    RescueBankInfo selectByPrimaryKey(String id);

    List<RescueBankInfo> selectAll();

    int updateByPrimaryKey(RescueBankInfo record);
}
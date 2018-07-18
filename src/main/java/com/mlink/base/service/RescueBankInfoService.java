package com.mlink.base.service;
//import com.mlink.base.entity.RescueBankInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by fudazhi on 2018/07/17.
 * 注:  需要回归接口添加 注解 ：@Transactional(rollbackFor = Exception.class)
 */


@Service
@EnableTransactionManagement
public class RescueBankInfoService  {
//    @Autowired(required = false)
//    private RescueBankInfo mapper;

}



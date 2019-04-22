package com.currency.base.service;

import com.currency.base.entity.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * 读写Redis
 * @Author: yhb
 * @Date: 2019/4/15 10:38
 */
@Service
@EnableTransactionManagement
public class RedisService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认存储空间头部名称
     */
    private static final String HEAD_SPACE = "test:";


    public void testAdd(){
        Material material = new Material ();
        material.setMaterialId ("11111111111111111");
        redisTemplate.opsForValue().set(HEAD_SPACE + "222222",material);
    }


}

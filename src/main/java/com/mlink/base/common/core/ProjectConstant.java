package com.mlink.base.common.core;

import org.springframework.stereotype.Component;

/**
 * 项目常量
 */
@Component
public  class ProjectConstant {
    public static  String BASE_PACKAGE = "com.mlink.base";//项目基础包名称，根据自己公司的项目修改

    public static  String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    public static  String MAPPER_PACKAGE = BASE_PACKAGE + ".mapper";//Mapper所在包
    public static  String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static  String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static  String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";//Controller所在包
    public static  String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".common.core.Mapper";//Mapper插件基础接口的完全限定名


}

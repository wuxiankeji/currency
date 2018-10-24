package com.mlink.base.common.configurer;

import com.github.pagehelper.PageInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Mybatis 配置
 * @author wyh
 * @version 2017/10/18.
 */
@Configuration
@MapperScan("com.**.**.mapper")
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    private String mapperLocations = "com.**.**.entity";
    private String typeAliasesPackage = "classpath:com/**/**/mapper/*.xml";

    @Autowired
    DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory(){

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //修复使用java -jar运行时，无法解析别名
        bean.setVfs(SpringBootVFS.class);

        //使别名配置支持通配符，并且可以有多个
        this.setTypeAliasesPackage(bean, typeAliasesPackage);

        //分页插件
        bean.setPlugins(new Interceptor[]{pageInterceptor()});

        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        //使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseGeneratedKeys(true);
        //使用列别名替换列名 select user as UserBean
        configuration.setUseColumnLabel(true);
        //使用驼峰命名属性映射字段userId => user_id
        configuration.setMapUnderscoreToCamelCase(true);
        //Long类型值为Null时
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        bean.setConfiguration(configuration);
        bean.setFailFast(true);

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(mapperLocations));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 使别名配置支持通配符，并且可以有多个
     */
    private void setTypeAliasesPackage(SqlSessionFactoryBean sqlSessionFactoryBean,String typeAliasesPackage) {
        final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
        typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;
        try {
            List<String> result = new ArrayList<>();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
            Resource[] resources = resolver.getResources(typeAliasesPackage);
            if (resources != null && resources.length > 0) {
                MetadataReader metadataReader;
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        try {
                            result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (result.size() > 0) {
                sqlSessionFactoryBean.setTypeAliasesPackage(StringUtils.join(result, ","));
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to set typeAliasesPackage");
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 开启事务注解
     */
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    //配置mybatis的分页插件pageHelper
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("params", "pageNum=page;pageSize=limit;orderBy=sort");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}

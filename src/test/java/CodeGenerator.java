import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置


    private static final String JDBC_URL = "jdbc:mysql://47.93.228.207:3306/kzjy?useUnicode=true&characterEncoding=UTF-8";
    private static final String JDBC_USERNAME = "dazhifu";
    private static final String JDBC_PASSWORD = "123456";
//    private static final String JDBC_URL = "jdbc:mysql://47.93.228.207:3306/kzjy?useUnicode=true&characterEncoding=UTF-8";
//    private static final String JDBC_USERNAME = "dazhifu";
//    private static final String JDBC_PASSWORD = "123456";

    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";




    private static final String PROJECT_PATH = System.getProperty("user.dir")+"/";//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";//模板位置

    private static final String JAVA_PATH = "src/main/java"; //java文件路径

    private static final String BASE_PACKAGE ="com.mlink.base";

    private static final String PACKAGE_PATH_ENTITY = BASE_PACKAGE + ".entity";
    private static final String PACKAGE_PATH_MAPPER =  BASE_PACKAGE + ".mapper";
    private static final String PACKAGE_PATH_SERVICE =  BASE_PACKAGE + ".service";
    private static final String PACKAGE_PATH_CONTROLLER =  BASE_PACKAGE + ".controller";

    private static final String AUTHOR = "fudazhi";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) {
//        String a= ProjectConstant.CCBIPADDRESS;

//        genModel("GSDATA_ARTICLES");
//        genModelAndMapper("RESCUE_BANK_INFO",null);
//mapperLocations

//        genCode("RESCUE_CUSTOMER_SERVICE");
        String aa=PACKAGE_PATH_ENTITY;
        genCodeCover("test");
//        genCode("GSDATA_ARTICLES");
//

//        genCode("RESCUE_ACTION");
////        genCode("RESCUE_PAY");
//        genCode("RESCUE_PRODUCT");
//        genCode("RESCUE_WX_INFO");
        //genCodeByCustomModelName("输入表名","输入自定义Model名称");


    }

    /**
     * 不会覆盖mapper xml
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName,null,false);
        }
    }
    /**
     * 会自动覆盖mapper xml
     * 通过数据表名称生成代码，entity 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public static void genCodeCover(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName,null,true);
        }
    }



    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName,boolean mapprIsCover) {
        genModelAndMapper(tableName, modelName,mapprIsCover);
        genService(tableName, modelName);
        genController(tableName, modelName);
    }


   // mapprIscover mapper是否覆盖
    public static void genModelAndMapper(String tableName, String modelName,boolean mapprIsCover) {

        if(mapprIsCover == true) {
            String modelNameUpperCamel = isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            File file = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_PATH_MAPPER) + modelNameUpperCamel + "Mapper.xml");
            if (file.getParentFile().exists()) {
                file.delete();
            }
        }
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS, "false");
        context.addProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS, "true");
        context.addProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE, "true");
        context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, "UTF-8");


        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(PACKAGE_PATH_ENTITY);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject( JAVA_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage(PACKAGE_PATH_MAPPER);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(PACKAGE_PATH_MAPPER);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (isNotEmpty(modelName))tableConfiguration.setDomainObjectName(modelName);
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);

            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }


        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) modelName = tableNameConvertUpperCamel(tableName);
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    public static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_PATH_SERVICE) + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

//            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
//            if (!file1.getParentFile().exists()) {
//                file1.getParentFile().mkdirs();
//            }
//            cfg.getTemplate("service-impl.ftl").process(data,
//                    new FileWriter(file1));
//            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public static void genController(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;

//            System.out.println("PPPPP="+modelNameUpperCamel);
//            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));  //类名
            data.put("baseRequestMapping", modelNameUpperCamel);
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH +  packageConvertPath(PACKAGE_PATH_CONTROLLER) + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }
}

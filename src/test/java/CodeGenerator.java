import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置

    private static final String JDBC_URL = "jdbc:mysql://xx.xxx.xxx.xxx:3306/xxxx?useUnicode=true&characterEncoding=UTF-8";
    private static final String JDBC_USERNAME = "xxxx";
    private static final String JDBC_PASSWORD = "xxxxxxx";

    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    // 包名需要根据实际进行配置
    private static final String BASE_PACKAGE ="com.mlink.base";


    private static final String PROJECT_PATH = System.getProperty("user.dir")+"/";//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";//模板位置

    private static final String JAVA_PATH = "src/main/java"; //java文件路径


    private static final String PACKAGE_PATH_ENTITY = BASE_PACKAGE + ".entity";
    private static final String PACKAGE_PATH_MAPPER =  BASE_PACKAGE + ".mapper";
    private static final String PACKAGE_PATH_SERVICE =  BASE_PACKAGE + ".service";
    private static final String PACKAGE_PATH_CONTROLLER =  BASE_PACKAGE + ".controller";

    private static final String AUTHOR = "fudazhi";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) {


        //genCode("user","测试类");

         // 只生成model,MAPPER
//        genModelAndMapper("survey_wx_user",null, true, "测试类");
    }

    /**
     * 生成entity、mapper、constroller，server、
     * @param tableName 数据表名称,
     *        describe 类描述
     */
    public static void genCode(String tableName,String describe) {
        genCodeByCustomModelName(tableName,null,true, describe);
    }
    /**
     * 生成entity、mapper、constroller，server、
     * @param tableName 数据表名称
     *        mapprIsCover 是否覆盖  true 覆盖false不覆盖
     *        describe 类描述
     */
    public static void genCode(String tableName,boolean mapprIsCover,String describe) {
        genCodeByCustomModelName(tableName,null,mapprIsCover, describe);
    }
    /**
     * 生成entity、mapper、constroller，server、
     * @param tableName 数据表名称
     *        modelName 表别名
     *        mapprIsCover 是否覆盖  true 覆盖false不覆盖
     *        describe 类描述
     */
    public static void genCode(String tableName, String modelName, boolean mapprIsCover,String describe) {
        genCodeByCustomModelName(tableName,modelName,mapprIsCover, describe);
    }


    /**
     * 生成entity、mapper、
     * @param tableName 数据表名称,
     *        describe 类描述
     */
    void genModelAndMapper(String tableName, String describe) {
        genModelAndMapper(tableName,null, true,describe);
    }

    /**
     * 生成entity、mapper、
     * @param tableName 数据表名称,
     *       mapprIsCover 是否覆盖  true 覆盖false不覆盖
     *        describe 类描述
     */
    void genModelAndMapper(String tableName, boolean mapprIsCover, String describe) {
        genModelAndMapper(tableName,null, mapprIsCover,describe);
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     *       mapprIsCover 是否覆盖
     *       describe     类描述
     */
    public static void genCodeByCustomModelName(String tableName, String modelName,boolean mapprIsCover, String describe) {
        genModelAndMapper(tableName, modelName,mapprIsCover,describe);
        genService(tableName, modelName);
        genController(tableName, modelName);
    }


   // mapprIscover mapper是否覆盖  describe：类描述
    public static void genModelAndMapper(String tableName, String modelName,boolean mapprIsCover, String describe) {
        String modelNameUpperCamel = isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
        if(mapprIsCover == true) {

            File file = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_PATH_MAPPER) + modelNameUpperCamel + "Mapper.xml");
            if (file.getParentFile().exists()) {
                file.delete();
            }
        }
        Context context = new Context(ModelType.FLAT);
        context.setId("mlimk");
        context.setTargetRuntime("MyBatis3Simple");


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
        //tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();


            config.addContext(context);
            CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
            commentGeneratorConfiguration.addProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS,"true");
            commentGeneratorConfiguration.addProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS, "false");
            commentGeneratorConfiguration.addProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS, "true");
//            commentGeneratorConfiguration.addProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE, "true");
            commentGeneratorConfiguration.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, "UTF-8");
            //commentGeneratorConfiguration.addProperty(PropertyRegistry.COMMENT_GENERATOR_AUTHOR, "mlink");

            context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
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

        // 添加类说明
        File file = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(PACKAGE_PATH_ENTITY) + modelNameUpperCamel + ".java");
        if (file.getParentFile().exists() && describe != null) {
            StringBuffer stringBuffer  =  new StringBuffer();
            List<String> list = new ArrayList<String>();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                // 防止路径乱码   如果utf-8 乱码  改GBK     eclipse里创建的txt  用UTF-8，在电脑上自己创建的txt  用GBK
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    if(line.contains("@ApiModel(")) {
                        stringBuffer.append("@ApiModel(value =\""+describe+"\")"+"\n");
                    } else {
                        stringBuffer.append(line+"\n");
                    }


                }
                br.close();
                isr.close();
                fis.close();

                FileWriter writer;
                writer = new FileWriter(file);
                writer.write(stringBuffer.toString());
                writer.flush();
                writer.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

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

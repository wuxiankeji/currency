package ${basePackage}.service;
import ${basePackage}.entity.${modelNameUpperCamel};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ${author} on ${date}.
 * 注:  需要回归接口添加 注解 ：@Transactional(rollbackFor = Exception.class)
 */


@Service
@EnableTransactionManagement
public class ${modelNameUpperCamel}Service  {
    @Autowired(required = false)
    private ${modelNameUpperCamel} mapper;

}



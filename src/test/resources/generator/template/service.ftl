package ${basePackage}.service;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
* Created by ${author} on ${date}.
* 注:  需要回归接口添加 注解 ：@Transactional(rollbackFor = Exception.class)
*/
@Service
@EnableTransactionManagement
public class ${modelNameUpperCamel}Service  {

    @Autowired(required = false)
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;


    public void insert(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.insert(${modelNameLowerCamel});
    }

    public void updateByPrimaryKey(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.updateByPrimaryKey(${modelNameLowerCamel});
    }

    public void deleteByPrimaryKey(String id) {
        ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(id);
    }

    public ${modelNameUpperCamel} selectByPrimaryKey(String id) {
        return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(id);
    }

    public List<${modelNameUpperCamel}> selectAll() {
        return ${modelNameLowerCamel}Mapper.selectAll();
    }

}



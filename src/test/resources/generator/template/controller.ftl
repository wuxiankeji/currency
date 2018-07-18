package ${basePackage}.controller;
import ${basePackage}.common.core.Result;
import ${basePackage}.common.core.ResultGenerator;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



/**
* Created by ${author} on ${date}.
*/
@Api(value="${modelNameUpperCamel} ",tags={"${baseRequestMapping}"})
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;
    @Autowired(required = false)
    private ${modelNameUpperCamel}Mapper ${modelNameUpperCamel}Mapper;


    @ApiOperation(value = "添加${modelNameUpperCamel}实体")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameUpperCamel}Mapper.insert(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "根据主键删除对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        ${modelNameUpperCamel}Mapper.deleteByPrimaryKey(id);
        return ResultGenerator.genSuccessResult();
    }



    @ApiOperation(value = "根据主键查询主键 对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=${modelNameUpperCamel}.class)})
    @GetMapping("/detail")
    public Result detail(@RequestParam String id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameUpperCamel}Mapper.selectByPrimaryKey(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }


    @ApiOperation(value = "查询全部")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=${modelNameUpperCamel}.class)})
    @GetMapping("/queryAll")
    public Result queryAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list;

        list = ${modelNameUpperCamel}Mapper.selectAll();

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}

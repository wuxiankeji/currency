package com.currency.base.controller;
import com.currency.base.entity.Material;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.currency.base.common.core.Result;
import com.currency.base.common.core.ResultGenerator;
import com.currency.base.service.MaterialService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
* Created by yuehaibin on 2018/10/24.
*/
@Api(value="Material ",tags={"Material"})
@RestController
@RequestMapping("Material")
public class MaterialController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private MaterialService materialService;

    @ApiOperation(value = "添加Material实体")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @PostMapping("/add")
    public Result add(@ApiParam (value="Material json对象") @RequestBody Material material) {
        materialService.insert(material);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "更新Material,如自动不传将会更新成null")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @PostMapping("/update")
    public Result update(@ApiParam (value="Material json对象") @RequestBody Material material) {
        materialService.updateByPrimaryKey(material);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "根据主键删除对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @GetMapping("/delete")
    public Result delete(@ApiParam (value="主键id") @RequestParam String id) {
        materialService.deleteByPrimaryKey(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "根据主键查询主键 对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Material.class)})
    @GetMapping("/detail")
    public Result detail(@ApiParam (value="主键id") @RequestParam String id) {
        Material material = materialService.selectByPrimaryKey(id);
        return ResultGenerator.genSuccessResult(material);
    }


    @ApiOperation(value = "查询全部")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Material.class)})
    @GetMapping("/queryAll")
    public Result queryAll(@ApiParam (value="请求第几页") @RequestParam(defaultValue = "0") Integer page,
                           @ApiParam (value="每页个数") @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Material> list;

        list = materialService.selectAll();

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}

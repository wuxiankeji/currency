package com.mlink.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mlink.base.common.core.Result;
import com.mlink.base.common.core.ResultGenerator;
import com.mlink.base.entity.Test;
import com.mlink.base.mapper.TestMapper;
import com.mlink.base.service.TestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by fudazhi on 2018/07/24.
*/
@Api(value="Test ",tags={"Test"})
@RestController
@RequestMapping("Test")
public class TestController {
    @Resource
    private TestService testService;
    @Autowired(required = false)
    private TestMapper TestMapper;


    @ApiOperation(value = "添加Test实体")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @PostMapping("/add")
    public Result add(@ApiParam(value="Test json对象") @RequestBody Test test) {
        TestMapper.insert(test);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "根据主键删除对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @GetMapping("/delete")
    public Result delete(@ApiParam (value="主键id") @RequestParam String id) {
        TestMapper.deleteByPrimaryKey(id);
        return ResultGenerator.genSuccessResult();
    }



    @ApiOperation(value = "根据主键查询主键 对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Test.class)})
    @GetMapping("/detail")
    public Result detail(@ApiParam (value="主键id") @RequestParam String id) {
        Test test = TestMapper.selectByPrimaryKey(id);
        return ResultGenerator.genSuccessResult(test);
    }


    @ApiOperation(value = "查询全部")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Test.class)})
    @GetMapping("/queryAll")
    public Result queryAll(@ApiParam (value="请求第几页") @RequestParam(defaultValue = "0") Integer page,
                           @ApiParam (value="每页个数") @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Test> list;

        list = TestMapper.selectAll();

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}

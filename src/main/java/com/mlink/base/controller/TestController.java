package com.mlink.base.controller;
import com.mlink.base.common.core.Result;
import com.mlink.base.common.core.ResultGenerator;
import com.mlink.base.entity.Test;
import com.mlink.base.service.TestService;
import com.mlink.base.mapper.TestMapper;
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
* Created by fudazhi on 2018/07/17.
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
    public Result add(@RequestBody Test test) {
        TestMapper.insert(test);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "根据主键删除对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        TestMapper.deleteByPrimaryKey(id);
        return ResultGenerator.genSuccessResult();
    }



    @ApiOperation(value = "根据主键查询主键 对象")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Test.class)})
    @GetMapping("/detail")
    public Result detail(@RequestParam String id) {
        Test test = TestMapper.selectByPrimaryKey(id);
        return ResultGenerator.genSuccessResult(test);
    }


    @ApiOperation(value = "查询全部")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Test.class)})
    @GetMapping("/queryAll")
    public Result queryAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Test> list;

        list = TestMapper.selectAll();

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}

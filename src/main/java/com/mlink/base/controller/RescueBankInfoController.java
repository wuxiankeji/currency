package com.mlink.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mlink.base.common.core.Result;
import com.mlink.base.common.core.ResultGenerator;
import com.mlink.base.entity.RescueBankInfo;
import com.mlink.base.mapper.RescueBankInfoMapper;
import com.mlink.base.service.RescueBankInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//import com.mlink.base.mapper.RescueBankInfoMapper;
//import com.mlink.base.entity.RescueBankInfo;



/**
* Created by fudazhi on 2018/07/17.
*/
@Api(value="RescueBankInfo ",tags={"RescueBankInfo"})
@RestController
@RequestMapping("RescueBankInfo")
public class RescueBankInfoController {
    @Resource
    private RescueBankInfoService rescueBankInfoService;
    @Autowired(required = false)
    private RescueBankInfoMapper RescueBankInfoMapper;

//
//    @ApiOperation(value = "添加RescueBankInfo实体,如果存在则更新")
//    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
//    @PostMapping("/addByPrimaryKey")
//    public Result add(@RequestBody RescueBankInfo rescueBankInfo) {
//
//        if (RescueBankInfoMapper.selectByPrimaryKey(rescueBankInfo) != null) {
//            RescueBankInfoMapper.updateByPrimaryKey(rescueBankInfo);
//        } else {
//            RescueBankInfoMapper.insert(rescueBankInfo);
//        }
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @ApiOperation(value = "根据主键删除对象")
//    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
//    @GetMapping("/deleteByPrimaryKey")
//    public Result deleteByPrimaryKey(@RequestParam String id) {
//        RescueBankInfoMapper.deleteByPrimaryKey(id);
//        return ResultGenerator.genSuccessResult();
//    }
//
//
//    @ApiOperation(value = "根据实体属性作为条件进行删除，查询条件使用等号")
//    @PostMapping("/delete")
//    public Result delete(@RequestBody RescueBankInfo rescueBankInfo) {
//        RescueBankInfoMapper.delete(rescueBankInfo);
//        return ResultGenerator.genSuccessResult();
//    }
//
//
//    @ApiOperation(value = "根据主键更新实体全部字段，null值会被更新")
//    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
//    @PostMapping("/update")
//    public Result update(@RequestBody RescueBankInfo rescueBankInfo) {
//
//        if (RescueBankInfoMapper.selectByPrimaryKey(rescueBankInfo) != null) {
//            RescueBankInfoMapper.updateByPrimaryKey(rescueBankInfo);
//        } else {
//            RescueBankInfoMapper.insert(rescueBankInfo);
//        }
//        return ResultGenerator.genSuccessResult();
//    }
//
//
//    @ApiOperation(value = "根据主键更新实体全部字段，null值不会被更新")
//    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Result.class)})
//    @PostMapping("/updateSelective")
//    public Result updateSelective(@RequestBody RescueBankInfo rescueBankInfo) {
//
//        if (RescueBankInfoMapper.selectByPrimaryKey(rescueBankInfo) != null) {
//            RescueBankInfoMapper.updateByPrimaryKeySelective(rescueBankInfo);
//        } else {
//            RescueBankInfoMapper.insert(rescueBankInfo);
//        }
//
//        return ResultGenerator.genSuccessResult();
//    }
//
//
//    @ApiOperation(value = "根据主键查询主键 对象")
//    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=RescueBankInfo.class)})
//    @GetMapping("/detail")
//    public Result detail(@RequestParam String id) {
//        RescueBankInfo rescueBankInfo = RescueBankInfoMapper.selectByPrimaryKey(id);
//        return ResultGenerator.genSuccessResult(rescueBankInfo);
//    }
//
//
//    @ApiOperation(value = "根据实体中的属性值进行查询，查询条件使用等号")
//    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=RescueBankInfo.class)})
//    @PostMapping("/query")
//    public Result query(@RequestBody RescueBankInfo rescueBankInfo, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<RescueBankInfo> list = RescueBankInfoMapper.select(rescueBankInfo);
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }


    @ApiOperation(value = "查询全部")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=RescueBankInfo.class)})
    @GetMapping("/queryAll")
    public Result queryAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RescueBankInfo> list;

        list = RescueBankInfoMapper.selectAll();

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

//    @ApiOperation(value = "根据实体中的属性查询总数，查询条件使用等号")
//    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功", response=Integer.class)})
//    @PostMapping("/queryCount")
//    public Result queryCount(@RequestBody RescueBankInfo rescueBankInfo) {
//        int count = RescueBankInfoMapper.selectCount(rescueBankInfo);
//        return ResultGenerator.genSuccessResult(count);
//    }

}

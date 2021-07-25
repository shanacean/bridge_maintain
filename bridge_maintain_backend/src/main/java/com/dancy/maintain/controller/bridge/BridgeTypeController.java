package com.dancy.maintain.controller.bridge;

import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.bridge.BridgeTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: maintain
 * @description: 桥梁类型控制层
 * @author: Bangyang Shan
 * @create: 2021-07-07 20:00
 **/
@RestController
@RequestMapping("/api/v1/bridgeType")
public class BridgeTypeController {
    @Autowired
    BridgeTypeServiceImpl bridgeTypeService;

    @GetMapping
    public Result getAll() {
        return ResultFactory.buildSuccessResult(bridgeTypeService.findAll());
    }

    @GetMapping("/page")
    public Result getAllByPage(Integer pageNum, Integer pageSize, String typeName) {
        if (typeName == null) typeName = "";
        return ResultFactory.buildSuccessResult(bridgeTypeService.findAllByPage(pageNum, pageSize, typeName));
    }
}
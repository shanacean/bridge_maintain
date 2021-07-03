package com.dancy.maintain.controller.bridge;

import com.dancy.maintain.pojo.bridge.Bridge;
import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.bridge.BridgeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: maintain
 * @description: 桥梁控制层
 * @author: Bangyang Shan
 * @create: 2021-07-02 19:49
 **/
@RestController
@RequestMapping("/api/v1/bridge")
public class BridgeController {

    @Autowired
    BridgeServiceImpl bridgeService;

    @PostMapping
    public Result addBridge(@RequestBody Bridge bridge) {
        Bridge newBridge = bridgeService.bridgeCreate(bridge);
        if (newBridge != null)
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("添加失败!");
    }

    @GetMapping("/page")
    public Result getAllByPage(Integer pageNum, Integer pageSize, String bridgeName) {
        System.out.println(bridgeService.findAllByPage(pageNum, pageSize, bridgeName));
        return ResultFactory.buildSuccessResult(bridgeService.findAllByPage(pageNum, pageSize, bridgeName));
    }

    @DeleteMapping
    public Result deleteBridge(Long bridgeId) {
        if (bridgeService.bridgeDelete(bridgeId))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("删除失败");
    }

}

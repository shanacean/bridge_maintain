package com.dancy.maintain.controller;

import com.dancy.maintain.pojo.intermediate.TypeComponent;
import com.dancy.maintain.pojo.intermediate.TypePart;
import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.monitor.MonitorContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: maintain
 * @description: 检测内容控制层
 * @author: Bangyang Shan
 * @create: 2021-07-08 23:08
 **/
@RestController
@RequestMapping("/api/v1/monitor")
public class MonitorContentController {
    @Autowired
    private MonitorContentServiceImpl monitorContentService;

    @PutMapping("/part")
    public Result editTypePart(@RequestBody List<TypePart> typeParts) {
        if (monitorContentService.updatePartWeight(typeParts))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("修改失败");
    }

    @PutMapping("/component")
    public Result editComponent(@RequestBody List<TypeComponent> typeComponents) {
        if (monitorContentService.updateComponentWeight(typeComponents))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("修改失败");
    }
}

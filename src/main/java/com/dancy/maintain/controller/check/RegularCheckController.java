package com.dancy.maintain.controller.check;

import com.dancy.maintain.pojo.check.RegularCheck;
import com.dancy.maintain.pojo.intermediate.RegularComponent;
import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.check.RegularCheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: maintain
 * @description: 定期检测控制层
 * @author: Bangyang Shan
 * @create: 2021-07-09 16:57
 **/
@RestController
@RequestMapping("/api/v1/regular")
public class RegularCheckController {
    @Autowired
    private RegularCheckServiceImpl regularCheckService;

    @GetMapping("/page")
    public Result getAllByPage(Integer pageNum, Integer pageSize, String regularId) {
        return ResultFactory.buildSuccessResult(regularCheckService.findAllByPage(pageNum, pageSize, regularId));
    }

    @PostMapping
    public Result addRegularCheck(@RequestBody RegularCheck regularCheck) {
        RegularCheck check = regularCheckService.regularCheckCreate(regularCheck);
        if (check != null)
            return ResultFactory.buildSuccessResult(check);
        return ResultFactory.buildFailResult("添加失败");
    }

    @PostMapping("/items")
    public Result addItems(@RequestBody List<RegularComponent> regularComponents) {
        if (regularCheckService.regularComponentList(regularComponents))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("添加失败！");
    }

    @GetMapping("/computed")
    public Result computedValue(Long regularId) {
        if ( regularCheckService.computed(regularId))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("计算失败！");
    }

    @DeleteMapping
    public Result deleteRegular(@RequestParam("regularId") Long regularId) {
        System.out.println(regularId);
        if (regularCheckService.regularDelete(regularId))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("删除失败!");
    }
}

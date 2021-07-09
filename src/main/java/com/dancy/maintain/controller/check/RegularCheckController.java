package com.dancy.maintain.controller.check;

import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.check.RegularCheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

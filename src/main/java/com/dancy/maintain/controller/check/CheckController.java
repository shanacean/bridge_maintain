package com.dancy.maintain.controller.check;

import com.dancy.maintain.dao.check.CheckItemDao;
import com.dancy.maintain.dao.check.DailyCheckDao;
import com.dancy.maintain.pojo.check.DailyCheck;
import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.check.CheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dailyCheck")
public class CheckController {
    @Autowired
    CheckServiceImpl checkService;

    @Autowired
    DailyCheckDao dailyCheckDao;

    @Autowired
    CheckItemDao checkItemDao;

    //增加或者修改一条日常检测信息
    @PostMapping
    public Result insertOne(@RequestBody DailyCheck dailyCheck){
        return ResultFactory.buildSuccessResult(dailyCheckDao.saveAndFlush(dailyCheck));
    }

    //通过检测id删除一条日常检测信息
    @DeleteMapping
    public Result deleteOneByCheckId(Long checkId){
        if (checkService.deleteOneByCheckId(checkId))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("删除失败");
    }

    //获取所有检测项
    @GetMapping("/checkItem")
    public Result findAllCheckItem(){
        return ResultFactory.buildSuccessResult(checkItemDao.findAll());
    }

    //通过分页获取所有日常检测信息
    @GetMapping("/page")
    public Result findAllByPage(Integer pageNum,Integer pageSize,String checkId){
        return ResultFactory.buildSuccessResult(checkService.findAllByPage(pageNum,pageSize,checkId));
    }

    //获取所有日常检测信息
    @GetMapping
    public Result findAllDailyCheck(){
        return ResultFactory.buildSuccessResult(dailyCheckDao.findAll());
    }

    //通过检测id获取一条日常检测信息
    @GetMapping("oneDailyCheck")
    public Result findOneDailyCheck(Long checkId){
        return ResultFactory.buildSuccessResult(dailyCheckDao.findOneByCheckId(checkId));
    }
}

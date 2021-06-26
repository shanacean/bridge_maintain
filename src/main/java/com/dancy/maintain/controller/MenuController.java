package com.dancy.maintain.controller;

import com.dancy.maintain.pojo.MainMenu;
import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultCode;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @program: bridge_maintain_backend
 * @description: 菜单控制层
 * @author: Bangyang Shan
 * @create: 2021-06-22 19:07
 **/
@RestController
@RequestMapping("/api/v1")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public Result listMenus() {
        HashMap<String, Object> res = new HashMap<>();
        List<MainMenu> menus = menuService.list();
        String message = "";
        if (menus != null) {
            res.put("menus", menus);
            return ResultFactory.buildSuccessResult(res);
        } else
            return ResultFactory.buildFailResult("菜单列表为空");
    }

}

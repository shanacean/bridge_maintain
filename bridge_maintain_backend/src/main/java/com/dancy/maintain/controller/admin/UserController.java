package com.dancy.maintain.controller.admin;

import com.dancy.maintain.pojo.admin.User;
import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.admin.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: bridge_maintain_backend
 * @description: User控制层
 * @author: Bangyang Shan
 * @create: 2021-06-23 23:24
 **/
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public Result getAllByPage(Integer pageNum, Integer pageSize, String username) {
        return ResultFactory.buildSuccessResult(userService.findAllByPage(pageNum, pageSize, username));
    }

    @GetMapping("/role")
    public Result getAllRoles() {
        return ResultFactory.buildSuccessResult(userService.findAllRoles());
    }

    @PostMapping
    public Result addUser(@RequestBody User user) {
        System.out.println(user);
        User res = userService.userCreate(user);
        System.out.println(res);
        if (res != null)
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("插入失败!");
    }

    @PutMapping
    public Result updateState(@RequestParam("id")Integer id, @RequestParam("state")Boolean state) {
        System.out.println("id:" + id + "state:" + state);
        int flag = userService.userStateUpdate(id, state);
        return flag > 0 ? ResultFactory.buildSuccessResult(null)
                : ResultFactory.buildFailResult("更新用户状态失败!");
    }

    @DeleteMapping
    public Result deleteUser(@RequestParam("id")Integer id) {
        if (userService.userDelete(id))
            return ResultFactory.buildSuccessResult(null);
        return ResultFactory.buildFailResult("删除失败,用户id不存在!");
    }
}

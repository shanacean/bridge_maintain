package com.dancy.maintain.controller.admin;

import com.dancy.maintain.pojo.admin.User;
import com.dancy.maintain.result.Result;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.service.admin.interf.UserService;
import com.dancy.maintain.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: bridge_maintain_backend
 * @description:
 * @author: Bangyang Shan
 * @create: 2021-06-19 09:02
 **/
@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            User userDB = userService.login(user);
            Map<String, Object> payload = new HashMap<>();
            payload.put("username", userDB.getUsername());
            String token = TokenUtil.getToken(payload);
            res.put("username", userDB.getUsername());
            res.put("token", token);
            res.put("rights", userDB.getRole());
            return ResultFactory.buildSuccessResult(res);
        }catch (Exception e) {
            return ResultFactory.buildFailResult("查无用户");
        }
    }

    @PostMapping("/user/check")
    public Result checkToken() {
        return ResultFactory.buildResult(200, "验证成功", null);
    }
}

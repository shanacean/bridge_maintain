package com.dancy.maintain.interceptors;

import com.dancy.maintain.result.ResultCode;
import com.dancy.maintain.result.ResultFactory;
import com.dancy.maintain.utils.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: bridge_maintain_backend
 * @description: token拦截器
 * @author: Bangyang Shan
 * @create: 2021-06-21 18:26
 **/

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在拦截器中设置允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");

        String authorization = request.getHeader("Authorization");
        String message = "";
        if (!ObjectUtils.isEmpty(authorization)) {
            try {
                TokenUtil.parseToken(authorization);
                return true;
            } catch (SignatureException e) {
                e.printStackTrace();
                message = "签名异常";
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
                message = "token过期";
            } catch (Exception e) {
                e.printStackTrace();
                message = "token无效";
            }
        } else {
            message = "无签名";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ResultFactory.buildResult(ResultCode.FAIL_AUTHORIZATION, message, null));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}

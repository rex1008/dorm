package com.dorm.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.dorm.bean.dto.TokenDTO;
import com.dorm.context.UserContext;
import com.dorm.enums.CodeEnum;
import com.dorm.ex.BizEx;
import com.dorm.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求路径:{}", request.getServletPath());
        String method = request.getMethod();
        if (HttpMethod.OPTIONS.matches(method)) {
            return true;
        }
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            throw new BizEx(CodeEnum.AUTH_ERR);
        }

        DecodedJWT decodedJWT = JwtUtil.verify(token);
        TokenDTO tokenDTO = JwtUtil.parse(decodedJWT, TokenDTO.class);
        if (tokenDTO == null) {
            throw new BizEx(CodeEnum.AUTH_ERR);
        }

        UserContext.set(tokenDTO);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}

package com.dorm.util;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dorm.enums.CodeEnum;
import com.dorm.ex.BizEx;

import java.util.Calendar;

public class JwtUtil {

    private static final String SECRET = "zhangsan";

    public static <T> String getToken(T t) {
        JWTCreator.Builder builder = JWT.create()
                .withClaim(t.getClass().getSimpleName(), JSONUtil.toJsonStr(t));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        return builder.withExpiresAt(c.getTime())
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verify(String token) {
        String error;
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token);
        } catch (AlgorithmMismatchException e) {
            error = "算法不匹配";
        } catch (InvalidClaimException e) {
            error = "声明无效";
        } catch (SignatureVerificationException e) {
            error = "签名不匹配";
        } catch (TokenExpiredException e) {
            error = "令牌失效";
        } catch (Exception e) {
            error = "令牌校验失败";
        }
        throw new BizEx(CodeEnum.AUTH_ERR);
    }

    public static <T> T parse(DecodedJWT decodedJWT, Class<T> clazz) {
        Claim claim = decodedJWT.getClaim(clazz.getSimpleName());
        if (claim == null) {
            return null;
        }
        return JSONUtil.toBean(claim.asString(), clazz);
    }
}

package com.qianms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

/**
 * JWT工具类
 */
public class JWTUtils {

    // 签发人
    private static final String ISSUSER = "itbaizhan";

    // 过期时间 5分钟
    private static  final long TOKEN_EXPIRE_TIME = 5  * 60 * 1000;

    // 秘钥
    private static final String KEY = "wer21w3e2r904923";



    /**
     * 生成令牌
     * @return
     */
    public static String token(){


        Date now = new Date();
        // key 用来加密数据签名秘钥
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        // 1. 创建JWT
        String token = JWT.create()
                //签发人
                .withIssuer(ISSUSER)
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                .sign(algorithm);
        return token;

    }


    /**
     * 验证令牌
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            // 1. 验证令牌
            JWTVerifier verifier = JWT.require(algorithm)
                    // 签发人
                    .withIssuer(ISSUSER)
                    .build();

            // 如果校验有问题会抛出异常
            verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }

        return false;

    }


    public static void main(String[] args) {


        //1. 生成令牌
//        String token = token();
//        System.out.println(token);
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJpdGJhaXpoYW4iLCJleHAiOjE2NDU5NDQzMjEsImlhdCI6MTY0NTk0NDAyMX0.BLHAKsHsVW4NUo7K_yZgaIq-64eI4R7_ewCl4svGntg

        // 令牌
        String token = "eyJ0eXAiOiJKVCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJpdCJleHAiOjE2NDU5NDQzMjEsImlhdCI6MTY0NTk0NDAyMX0.BLHAKsHsVW4NUo7K_yZgaIq-64eI4R7_ewCl4svGntg";
        boolean verify = verify(token);
        System.out.println(verify);

    }


}

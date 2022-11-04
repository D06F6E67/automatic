package com.lee.automatic.common.utils;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;

import java.util.Map;

/**
 * JWT工具类
 *
 * @author lee
 */
public class JwtUtils {


    private static final int EXPIRE = 10 * 24 * 60;

    /**
     * 生成ES256加密token
     *
     * @param data       有效数据
     * @param privateKey 密钥
     * @return token
     */
    public static String createEs256JwtToken(Map<String, Object> data, String privateKey) {

        JWTAuth provider = JWTAuth.create(Vertx.vertx(), new JWTAuthOptions()
                .addPubSecKey(new PubSecKeyOptions()
                        .setAlgorithm("ES256")
                        .setBuffer(privateKey)
                ));

        return provider.generateToken(
                new JsonObject(data),
                new JWTOptions()
                        .setExpiresInMinutes(EXPIRE)
                        .setAlgorithm("ES256"));
    }
}
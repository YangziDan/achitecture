package edu.ynu.se.xiecheng.achitectureclass.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class tokenUtil {
    public static final long EXPIRE_DATE=7*24*60*60*1000;
    //token过期时间
    public static final String TOKEN_SECRET="mySecret";
    //token加密密钥
    public static String token (String account,String password){
        if(account.isEmpty()||password.isEmpty())
            return "Wrong input";

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带account，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("account",account)
                    .withClaim("password",password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }
    public static DecodedJWT decode(String token){
        //解码token,如果token不正确,则返回null
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        }catch (TokenExpiredException e){
            return  null;
        }
    }
}

package cn.arcy.jportal.portal.constant;

import org.springframework.util.DigestUtils;

public final class RedisKey {

    /**
     * jwt key
     */
    public final static String JWT = "jwt:token:";

    public static String getJwtTokenPrefix(String clientId)
    {
        return RedisKey.JWT + clientId + ':';
    }

    public static String getJwtTokenKey(String clientId, String token)
    {
        String md5Token = DigestUtils.md5DigestAsHex(token.getBytes());
        return getJwtTokenPrefix(clientId) + md5Token;
    }

    private RedisKey(){}
}

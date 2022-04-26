package com.java1234.entity;

/**
 * HttpParame Description: 请求参数
 */
public class HttpParame {

    // 应用唯一标识
    public static final String APPID = "appid";

    // 密匙
    public static final String SECRET = "secret";

    // 微信用户唯一标识
    public static final String OPENID = "openid";

    // 接口调用凭证
    public static final String ACCESS_TOKEN = "access_token";

    // 回调地址
    public static final String REDIRECT_URI = "redirect_uri";

    // 网页授权回调地址
    public static final String AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=wxbdc5610cc59c1631&redirect_uri=https%3A%2F%2Fpassport.yhd.com%2Fwechat%2Fcallback.do&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect";

    // 通过code获取access_token
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    // 此接口用于获取用户个人信息 UnionID机制
    public static final String GET_UNIONID_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

}

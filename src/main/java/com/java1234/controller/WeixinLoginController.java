package com.java1234.controller;

import com.alibaba.fastjson.JSONObject;
import com.java1234.constanst.Constanst;
import com.java1234.entity.AccessToken;
import com.java1234.entity.WechatUserUnionID;
import com.java1234.service.WeixinLoginService;
import com.java1234.util.AesUtil;
import com.java1234.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

/**
 * 微信扫码登录Controller
 */

@Controller
public class WeixinLoginController {

    @Autowired
    private WeixinLoginService weixinLoginService;


//    @GetMapping("/weixinLogin")
//    private ResultJson weixinLogin(){
//        try {
////            String token1=weiXinShareService.setAccessToken();
//            //获取token开发者
//            String token=weiXinShareService.getAccessToken();
//            String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
//            String scene_str = getRandomString(8);//这里生成一个带参数的二维码，参数是scene_str
//            String json="{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+scene_str+"\"}}}";
//            System.out.println(json);
//            JSONObject jsonObject = JSONObject.parseObject(json);
//            JSONObject jsonObject1 = HttpUtils.httpPost(url,jsonObject);
//            jsonObject1.put("sceneStr",scene_str);
//            System.out.println("获取微信登录二维码成功！！！！"+jsonObject1.toJSONString());
//            return ResultJson.ok(jsonObject1);
//        } catch (Exception e) {
//            System.out.println("获取微信登录二维码失败:"+e);
//            return ResultJson.failure(ResultCode.BAD_REQUEST);
//        }
//    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }



    /**
     * 重定向到微信扫码登录地址
     *
     * @return
     */
    @GetMapping("/weixinLogin")
    public String weixinLogin() {
        String url = weixinLoginService.genLoginUrl();
        return "redirect:" + url;
    }

    /**
     * 回调地址处理
     *
     * @param code
     * @param state
     * @return
     */
    @GetMapping(value = "/weixinconnect")
    public ModelAndView callback(String code, String state) {
        String access_token = null;
        String openid = null;
        ModelAndView mav = new ModelAndView();
        if (code != null && state != null) {
            // 验证state为了用于防止跨站请求伪造攻击
            String decrypt = AesUtil.decrypt(AesUtil.parseHexStr2Byte(state), AesUtil.PASSWORD_SECRET_KEY, 16);
            if (!decrypt.equals(Constanst.PWD_MD5 + DateUtil.getYYYYMMdd())) {
                mav.addObject("error", "登录失败，请联系管理员！");
                mav.setViewName("loginError");
                return mav;
            }
            AccessToken access = weixinLoginService.getAccessToken(code);
            if (access != null) {
                // 把获取到的access_token和openId赋值给变量
                access_token = access.getAccess_token();
                openid = access.getOpenid();

                // 存在则把当前账号信息授权给扫码用户
                // 拿到openid获取微信用户的基本信息

                // 此处可以写业务逻辑
                if (access_token == null || openid == null) {
                    return null;
                }
                WechatUserUnionID userUnionID = weixinLoginService.getUserUnionID(access_token, openid);
                mav.addObject("userInfo", userUnionID);
                mav.setViewName("main");
                return mav;

            }
        }
        return null;
    }


}

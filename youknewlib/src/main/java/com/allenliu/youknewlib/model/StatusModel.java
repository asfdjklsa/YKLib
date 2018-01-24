package com.allenliu.youknewlib.model;

/**
 * Created by allenliu on 2018/1/23.
 */

public class StatusModel {

    /**
     * success : true
     * AppConfig : {"PushKey":null,"AcceptCount":0,"AppId":"wuheng20180123","ShowWeb":"1","Del":"0","Url":"http://www.baidu.com/","Remark":"接口说明：appid为唯一标示APP的字符串，调用前需要配置好。需要用到的返回值：【success】：布尔值，true 调用成功，false 请求失败，出错的情况一般就是appid传错了。【ShowWeb】：字符串，\"0\"不跳转， \"1\"跳转【PushKey】：字符串，推送用的key【Url】：字符串 跳转的url地址。"}
     */

    public boolean success;
    public AppConfigBean AppConfig;

    public static class AppConfigBean {
        /**
         * PushKey : null
         * AcceptCount : 0
         * AppId : wuheng20180123
         * ShowWeb : 1
         * Del : 0
         * Url : http://www.baidu.com/
         * Remark : 接口说明：appid为唯一标示APP的字符串，调用前需要配置好。需要用到的返回值：【success】：布尔值，true 调用成功，false 请求失败，出错的情况一般就是appid传错了。【ShowWeb】：字符串，"0"不跳转， "1"跳转【PushKey】：字符串，推送用的key【Url】：字符串 跳转的url地址。
         */

        public String PushKey;
        public int AcceptCount;
        public String AppId;
        public String ShowWeb;
        public String Del;
        public String Url;
        public String Remark;
    }
}

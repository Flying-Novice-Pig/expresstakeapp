package com.graduationdesign.expresstakeapp.util.jpush;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

@Component
public class PushAndroid {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PushAndroid.class);
    private String appKey = "cc6f35923ec6239f3e69f6f0";
    private String masterSecret = "e33767899ea79753eb1295d4";
    private String pushUrl = "https://api.jpush.cn/v3/push";
    private boolean apns_production = true;
    private int time_to_live = 86400;
    private static final String ALERT = "推送信息";

    /**
     * 极光推送
     */
    public void jiguangPush(){
        String alias = "123456";//声明别名
        try{
            String result = push(pushUrl,alias,ALERT,appKey,masterSecret,apns_production,time_to_live);
            JSONObject resData = JSONObject.fromObject(result);
            if(resData.containsKey("error")){
                log.info("针对别名为" + alias + "的信息推送失败！");
                JSONObject error = JSONObject.fromObject(resData.get("error"));
                log.info("错误信息为：" + error.get("message").toString());
            }
            log.info("针对别名为" + alias + "的信息推送成功！");
        }catch(Exception e){
            log.error("针对别名为" + alias + "的信息推送失败！",e);
        }
    }

    /**
     * 组装极光推送专用json串
     * @param alias
     * @param alert
     * @return json
     */
    public static JSONObject generateJson(String alias,String alert,boolean apns_production,int time_to_live){
        JSONObject json = new JSONObject();
        JSONArray platform = new JSONArray();//平台
        platform.add("android");
//        platform.add("ios");

        JSONObject audience = new JSONObject();//推送目标
        JSONArray alias1 = new JSONArray();
        alias1.add(alias);
        audience.put("alias", alias1);

        JSONObject notification = new JSONObject();//通知内容
        JSONObject android = new JSONObject();//android通知内容
        android.put("alert", alert);
        android.put("builder_id", 1);
        JSONObject android_extras = new JSONObject();//android额外参数
        android_extras.put("type", "infomation");
        android.put("extras", android_extras);

        notification.put("android", android);

        JSONObject options = new JSONObject();//设置参数
        options.put("time_to_live", Integer.valueOf(time_to_live));
        options.put("apns_production", apns_production);

        json.put("platform", platform);
        json.put("audience", audience);
        json.put("notification", notification);
        json.put("options", options);
        return json;

    }

    /**
     * 推送方法-调用极光API
     * @param reqUrl
     * @param alias
     * @param alert
     * @return result
     */
    public static String push(String reqUrl,String alias,String alert,String appKey,String masterSecret,boolean apns_production,int time_to_live){
        String base64_auth_string = encryptBASE64(appKey + ":" + masterSecret);
        String authorization = "Basic " + base64_auth_string;
        return sendPostRequest(reqUrl,generateJson(alias,alert,apns_production,time_to_live).toString(),"UTF-8",authorization);
    }

    /**
     * 发送Post请求（json格式）
     * @param reqURL
     * @param data
     * @param encodeCharset
     * @param authorization
     * @return result
     */
    @SuppressWarnings({ "resource" })
    public static String sendPostRequest(String reqURL, String data, String encodeCharset,String authorization){
        HttpPost httpPost = new HttpPost(reqURL);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            StringEntity entity = new StringEntity(data, encodeCharset);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization",authorization.trim());
            response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), encodeCharset);
        } catch (Exception e) {
            log.error("请求通信[" + reqURL + "]时偶遇异常,堆栈轨迹如下", e);
        }finally{
            client.getConnectionManager().shutdown();
        }
        return result;
    }
    /**
     　　　　* BASE64加密工具
     　　　　*/
    public static String encryptBASE64(String str) {
        byte[] key = str.getBytes();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String strs = base64Encoder.encodeBuffer(key);
        return strs;
    }

}

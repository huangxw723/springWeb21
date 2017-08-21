package util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import sprittr.config.SmsConfig;


/**
 * 通过短信接口发送短信
 */
//@Component
public class SmsUtils {

    public static void main(String[] args) {

    	sendSms2("18928773717", "【非诚勿扰】恭喜您!您的手机号码已被江苏卫视【非诚勿扰】"
    			+ "第683期' 爱琴海见证我们的幸福！'，获得爱琴海7天6晚 旅游券两张"
    			+ "详情请使用电脑登陆本次活动官方网站: tv.jstv.com/fcwr/ 查看领取，您的兑奖收码为【JS20170722X】请小心保管."
    			+ "如您将个人获奖信息泄露导致他人冒名领取本栏目组概不负责，最终解释权归江苏卫视电视台【非诚勿扰】");
        // sendSmsAll(List<PageData> list)

        // sendSms1();
    }

    // 短信商 一 http://www.dxton.com/
    // =====================================================================================
    @Autowired
    SmsConfig smsConfig;
    /**
     * 给一个人发送单条短信
     *
     * @param mobile
     *            手机号
     * @param code
     *            短信内容
     */

    public static void sendSms1(String mobile, String code) {
//        String account = "", password = "";
//
//        String strSMS1 = smsConfig.getSms1(); // 读取短信1配置
//        if (null != strSMS1 && !"".equals(strSMS1)) {
//            String strS1[] = strSMS1.split(",fh,");
//            if (strS1.length == 2) {
//                account = strS1[0];
//                password = strS1[1];
//            }
//        }
        String PostData = "";
        try {
            PostData = "account=" + "AA000160" + "&password=" + "AA00016020" + "&mobile=" + mobile + "&content=" + URLEncoder.encode(code, "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("短信提交失败");
        }
        // System.out.println(PostData);
        String ret = SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
        System.out.println(ret);
		/*
		 * 100 发送成功 101 验证失败 102 手机号码格式不正确 103 会员级别不够 104 内容未审核 105 内容过多 106
		 * 账户余额不足 107 Ip受限 108 手机号码发送太频繁，请换号或隔天再发 109 帐号被锁定 110 发送通道不正确 111
		 * 当前时间段禁止短信发送 120 系统升级
		 */

    }

    public static String SMS(String postData, String postUrl) {
        try {
            // 发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            // 获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            // 获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }

    // ===================================================================================================================

    /**
     *
     * 短信商 二 http://dx.ipyy.net/ ==============================================
     * =======================================
     *
     */
    private static String Url = "http://114.113.154.5/sms.aspx?action=send";

    /**
     * 给一个人发送单条短信
     *
     * @param mobile
     *            手机号
     * @param content
     *            短信内容
     */
    public static  void sendSms2(String mobile, String content) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");


        content += ",打死也不要告诉别人窝.";

        NameValuePair[] data = {// 提交短信
                new NameValuePair("account","AA000160"), new NameValuePair("password", "AA00016020"), // 密码可以使用明文密码或使用32位MD5加密
                new NameValuePair("mobile", mobile), new NameValuePair("content", content), };

        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult = method.getResponseBodyAsString();

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String status = root.elementText("returnstatus");
            String message = root.elementText("message");
            String taskID = root.elementText("taskID");

            System.out.println(status);
            System.out.println(message);
            System.out.println(taskID);
            if (status == "Success") {
                System.out.println("短信提交成功");
            }

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 给多个人发送单条短信
     *
     * @param list
     *            手机号验证码
     */
    public  void sendSmsAll(List<Map> list) {
        String code;
        String mobile;
        for (int i = 0; i < list.size(); i++) {
            code = list.get(i).get("code").toString();
            mobile = list.get(i).get("mobile").toString();
            sendSms2(mobile, code);
        }
    }

}

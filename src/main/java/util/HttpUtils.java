package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

	private static HttpUtils instance;
	private static HttpClient httpClient;

	public synchronized static HttpUtils getInstance() {
		if (instance == null) {
			instance = new HttpUtils();
			httpClient = HttpClients.createDefault();
		}
		return instance;
	}

	/**
	 * 
	 * @param path
	 * @param params
	 *            模拟http post
	 * @param encode
	 * @return
	 */
	public String sendPostMethod(String path, Map<String, Object> params, String encoding) {
		HttpPost httpPost = new HttpPost(path);
		String result = "";
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		try {
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					String name = entry.getKey();
					String value = entry.getValue().toString();
					BasicNameValuePair valuePair = new BasicNameValuePair(name, value);
					parameters.add(valuePair);
				}
			}
			// 纯文本表单，不包含文件
			UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(parameters, encoding);
			httpPost.setEntity(encodedFormEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param path
	 * @param params
	 *            模拟http get
	 * @param encode
	 * @return
	 */
	public String sendGetMethod(String path, Map<String, Object> params, String encoding) throws Exception {
		String result = "";
		String parameters = "";

		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String name = entry.getKey();
				String value = entry.getValue().toString();
				parameters = parameters + name + "=" + value + "&";
			}
			parameters = parameters.substring(0, parameters.length() - 1);
		}

		HttpGet httpGet = new HttpGet(path + "?" + parameters);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			result = EntityUtils.toString(httpResponse.getEntity(), encoding);
		}

		return result;
	}


	/**
	 * 获取 订单快递进度
	 *
	 * @date 2016年8月24日
	 * @param principle
	 * @param orderId
	 *            订单ID
	 * @return
	 */
	public static void main(String[] args){
		String result = HttpUtils.getInstance().sendPostMethod(
				"https://www.kuaidi100.com/autonumber/autoComNum", CommonUtils
						.getConditions().putData("text", "402523521065"),
				"UTF-8");
		JSONObject shipping = JSON.parseObject(result);
		JSONArray auto = JSON.parseArray(shipping.get("auto").toString());
		List<Object> IMsgList = new LinkedList<Object>();

		if (!auto.isEmpty()) {

			JSONObject first = auto.getJSONObject(0);
			String code = first.getString("comCode");

			// 查询快递进度信息
			Conditions params = CommonUtils.getConditions();
			params.putData("type", code).putData("postid", "402523521065");
			result = HttpUtils
					.getInstance()
					.sendPostMethod(
							"https://www.kuaidi100.com/query?id=1&valicode=&temp=0.6654796168792949",
							params, "UTF-8");
			if (result.trim().isEmpty()) {
				Assert.notNull(null, "系统繁忙");
			}

			JSONObject message = JSON.parseObject(result);
			JSONArray msgList = message.getJSONArray("data");

			for (int idx = msgList.size() - 1; idx >= 0; idx--) {
				JSONObject msg = JSON.parseObject(msgList.get(idx).toString());
				IMsgList.add(msg);
			}
		}

		System.out.println(IMsgList);
	}
}

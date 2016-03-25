package cn.com.bugu.weixin.untils;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
	/**
	 * 将java对象转为json字符串
	 * @param obj
	 * @return
	 */
	public static String beanToJson(Object obj){
		return JSONObject.toJSONString(obj);
	}
}

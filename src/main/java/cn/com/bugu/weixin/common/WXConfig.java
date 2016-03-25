package cn.com.bugu.weixin.common;

import java.util.Map;

import cn.com.bugu.weixin.untils.PathUtil;
import cn.com.bugu.weixin.untils.PropertiesUtil;

public class WXConfig {
	private static WXConfig wxcfg;
	private static Map<String,String> wxcfgMap;
	private static Map<String,String> keywordMap;
	private static Map<String,String> newsMap;
	public enum MK{
		WX_DOMAIN,WX_URL,WX_TOKEN,WX_APPID,WX_APPSECRET,KEY,MCHID
	}
	private WXConfig(){}
	
	/**
	 * 单例模式
	 * @return
	 */
	public static WXConfig getInstance(){
		if(null==wxcfg){
			init();
			wxcfg = new WXConfig();
		}
		return wxcfg;
	}
	
	public Map<String,String> getWXConfig(){
		return wxcfgMap;
	}
	
	public Map<String,String> getKeywordConfig(){
		return keywordMap;
	}
	
	public Map<String,String> getNewsConfig(){
		return newsMap;
	}
	
	private static void init(){
		String wxcfgFile = PathUtil.getClassPath().concat("config/wxconfig.properties");
		wxcfgMap = PropertiesUtil.readPropertiesToMap(wxcfgFile);
		
		String rspkeyFile = PathUtil.getClassPath().concat("config/keyword.properties");
		keywordMap = PropertiesUtil.readPropertiesToMap(rspkeyFile);
		
		String newsFile = PathUtil.getClassPath().concat("config/news.properties");
		newsMap = PropertiesUtil.readPropertiesToMap(newsFile);
	}
}

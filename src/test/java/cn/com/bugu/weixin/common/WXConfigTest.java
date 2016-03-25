package cn.com.bugu.weixin.common;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class WXConfigTest {
	@Test
	public void testGetInstance(){
		WXConfig wxcfg = WXConfig.getInstance();
		Map<String,String> map = wxcfg.getWXConfig();
		//不为NULL测试
		Assert.assertNotNull(map.get("WX_APPID"));
		Assert.assertNotNull(map.get("WX_URL"));
		Assert.assertNotNull(map.get("WX_DOMAIN"));
		Assert.assertNotNull(map.get("WX_TOKEN"));
		Assert.assertNotNull(map.get("WX_APPSECRET"));
		Assert.assertNotNull(map.get("KEY"));
		Assert.assertNotNull(map.get("MCHID"));
		//不为空字符串测试
		Assert.assertFalse("".equals(map.get("WX_APPID")));
		Assert.assertFalse("".equals(map.get("WX_URL")));
		Assert.assertFalse("".equals(map.get("WX_DOMAIN")));
		Assert.assertFalse("".equals(map.get("WX_TOKEN")));
		Assert.assertFalse("".equals(map.get("WX_APPSECRET")));
		Assert.assertFalse("".equals(map.get("KEY")));
		Assert.assertFalse("".equals(map.get("MCHID")));
		
	}
}

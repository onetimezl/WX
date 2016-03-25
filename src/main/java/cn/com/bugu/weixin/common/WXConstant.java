package cn.com.bugu.weixin.common;

public class WXConstant {
	//微信平台消息类型
	public enum MESSAGE_TYPE{
		/*	说明：
		 *  music(音乐)
		 *  news(图文)
		 *  text(文本)
		 *  image(图片)
		 *  link(链接)
		 *  location(地理位置)
		 *  voice(音频)
		 *  event(推送)
		 */
		music,news,text,image,link,location,voice,event
	}
	
	//微信平台事件类型
	public enum EVENT_TYPE{
		/*	说明：
		 *  subscribe(订阅
		 *  unsubscribe(取消订阅)
		 *  CLICK(自定义菜单点击事件)
		 *  VIEW(图文事件)
		 */
		subscribe,unsubscribe,CLICK,VIEW
	}
	
	//微信平台请求XML内容的Key
	public enum REQUEST_KEY{
		FromUserName,ToUserName,MsgType
	}
	
	//微信平台请求XML内容的Key
	public enum RSP_STATUS{
		SUCCESS,FAIL,OK,CANCLE
	}
	
	//请求的类型
	public enum ReqMethod {
		GET,POST;
	}
	
	//日志记录名称
	public static final String WX_LOG_NAME="WXLog";
	public static final String WX_ORDER_LOG_NAME="orderLogger";
	
	//微信官网URL
    public final static String WEIXIN_WEBSITE="https://mp.weixin.qq.com";
    
    //获取access_token的接口地址（GET） 限200（次/天）
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
   
    //菜单创建（POST） 限100（次/天）
    public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	
	//常量值Key
//	public static final String FROM_USER_NAME = "FromUserName";
//	public static final String TO_USER_NAME = "ToUserName";
//	public static final String MSG_TYPE = "MsgType";
	public static final String PROJECT_URL = "PROJECT_URL";
	
	//状态值
//	public static final String WX_SUCCESS="SUCCESS";
//	public static final String WX_FAIL="FAIL";
//	public static final String WX_OK="OK";
//	public static final String WX_CANCLE="CANCLE";
	
	//用户的微信OpenId在session中的Key值
	public static final String WX_USER_STATE="WX_USER_STATE";
	public static final String WX_USER_OPENID="WX_USER_OPENID";
	//public static final String CATALOG_PARENT_IDS="CATALOG_PARENT_IDS";
	
	//微信Auto2.0中设置的state的值
	public static final String WX_AUTH20_STATE1_VAL="1";//预约服务
	public static final String WX_AUTH20_STATE2_VAL="2";//我的订单
	public static final String WX_AUTH20_STATE3_VAL="3";//图文进入
	
	public static final String WX_USERLOGIN_TYPE="userLoginType";//用户登录方式
	public static final String WX_USERLOGIN_TYPE_VIEW="VIEW";//图文进入
	public static final String WX_USERLOGIN_TYPE_MENU="MENU";//通过菜单进入
	

}

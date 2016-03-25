package cn.com.bugu.weixin.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.bugu.weixin.req.vo.TextMessage;
import cn.com.bugu.weixin.rsp.vo.Article;
import cn.com.bugu.weixin.rsp.vo.NewsMessage;
import cn.com.bugu.weixin.untils.PathUtil;



/**
 * 微信平台消息处理
 * @author onetime
 *
 */
public class WXMessageHandle {
	private static Log log  = LogFactory.getLog(WXConstant.WX_LOG_NAME);
	
	private String processRequest(HttpServletRequest request) {
		String rspMessage = null;
		String projectUrl = PathUtil.getProjectUrl(request);
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> reqParms = WXParse.parseReqXml(request);
			reqParms.put(WXConstant.PROJECT_URL, projectUrl);
			reqParms.put("SESSION_ID",request.getSession().getId());
//			// 发送方帐号（open_id）
//			String fromUserName = reqParms.get("FromUserName");
//			// 公众帐号
//			String toUserName = reqParms.get("ToUserName");
			// 消息类型
			String msgType = reqParms.get("MsgType");
	
			if(msgType.equals(WXConstant.MESSAGE_TYPE.text)) { // 文本消息
				return processText(reqParms);
			}else if (msgType.equals(WXConstant.MESSAGE_TYPE.event)) {// 事件推送
				return processEvent(reqParms);
			}
//			}else if(msgType.equals(WXConstant.MESSAGE_TYPE.image)){ //图片
//				
//			}else if(msgType.equals(WXConstant.MESSAGE_TYPE.news)){ //图文消息
//				
//			}else if(msgType.equals(WXConstant.MESSAGE_TYPE.link)){ //链接
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rspMessage;
	}
	
	/**
	 * 处理文本消息
	 * @param reqParms
	 * @return
	 */
	private String processText(Map<String,String> reqParms){
		String returnMsg = "";
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(reqParms.get(WXConstant.REQUEST_KEY.FromUserName.name()));
		textMessage.setFromUserName(reqParms.get(WXConstant.REQUEST_KEY.ToUserName.name()));
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WXConstant.MESSAGE_TYPE.text.name());
		String content = reqParms.get("Content");
		//关键字处理
		Map<String,String> keywordMap = WXConfig.getInstance().getKeywordConfig();
		Map<String,String> newsMap = WXConfig.getInstance().getNewsConfig();
		String jdaz_key = "京东安装";
		String nihao="你好";
		String zaima="在吗";
		//如果用户输入"你好"或"在吗"回复关注图文
		if(nihao.equals(content) || zaima.equals(content)){
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(reqParms.get(WXConstant.REQUEST_KEY.FromUserName));
			newsMessage.setFromUserName(reqParms.get(WXConstant.REQUEST_KEY.ToUserName));
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(WXConstant.MESSAGE_TYPE.news.name());
			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle(newsMap.get("KEY_WORD_TITLE"));
			article.setPicUrl(reqParms.get(WXConstant.PROJECT_URL) + newsMap.get("KEY_WORD_PICURL1"));
			article.setDescription(newsMap.get("KEY_WORD_DESCRIPTION"));
			String url = newsMap.get("KEY_WORD_VIEW_URL");
			article.setUrl(url);
			articleList.add(article);
			
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			returnMsg = WXParse.newsMessageToXml(newsMessage);
			log.info(returnMsg);
			return returnMsg;
		}
		
		if(keywordMap.containsKey(content)){
			if(jdaz_key.equals(content)){
				String jdaz_val = keywordMap.get(jdaz_key);
				returnMsg = jdaz_val.replaceAll("Enter", "\n\n");
			}else{
				returnMsg = keywordMap.get(content).toString();
			}
		}
		if(StringUtils.isEmpty(returnMsg)){
			return returnMsg;
		}
		textMessage.setContent(returnMsg);
		returnMsg = WXParse.textMessageToXml(textMessage);
		return returnMsg;
	}
	
	/**
	 * 处理事件
	 * @param reqParms
	 * @return
	 */
	private String processEvent(Map<String,String> reqParms){
		String returnMsg = "";
		// 事件类型
		String eventType = reqParms.get("Event");
		log.debug("WeiXinController-->processEvent eventType::"+eventType);
		log.info("eventType::"+eventType);
		// 关注
		if (eventType.equals(WXConstant.MESSAGE_TYPE.event)) { 
			log.debug("WeiXinController-->processEvent EVENT_TYPE_SUBSCRIBE");
			// 发送图文消息
			return processNewsMessage(reqParms);
		// 取消关注
		} else if (eventType.equals(WXConstant.EVENT_TYPE.unsubscribe)) {
			log.debug("WeiXinController-->processEvent EVENT_TYPE_UNSUBSCRIBE");
			
			log.debug("WeiXinController-->SessionId::"+reqParms.get("SESSION_ID"));
			
			log.debug("WeiXinController-->removeAttribute：："+WXConstant.WX_USER_OPENID);
			
		//点击事情
		}else if(eventType.equals(WXConstant.EVENT_TYPE.CLICK)){
			String eventKey = reqParms.get("EventKey");
			log.info("eventKey::"+eventKey);
			log.info("processEvent-->respMessage::"+returnMsg);
		}
		return returnMsg;
	}
	
	/**
	 * 处理关注图文
	 * @param requestMap
	 * @return
	 */
	private String processNewsMessage(Map<String, String> requestMap){
		String returnMsg = "";
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(requestMap.get(WXConstant.REQUEST_KEY.FromUserName));
		newsMessage.setFromUserName(requestMap.get(WXConstant.REQUEST_KEY.ToUserName));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WXConstant.MESSAGE_TYPE.news.name());
		List<Article> articleList = new ArrayList<Article>();
		//第一个图文
		Map<String,String> newsMap = WXConfig.getInstance().getNewsConfig();
		Article article = new Article();
		article.setTitle(newsMap.get("SUBSCRIBE_TITLE"));
		article.setPicUrl(WXConstant.PROJECT_URL + newsMap.get("SUBSCRIBE_PICURL1"));
		article.setDescription(newsMap.get("SUBSCRIBE_DESCRIPTION"));
		String url = newsMap.get("GUGU_VIEW_URL");//登录的url
		article.setUrl(url);
		/*
		//第二个图文
		Article article1 = new Article();
		article1.setTitle(Constants.GUGU_VIEW1_TITLE);
		article1.setPicUrl(requestMap.get(Constants.PROJECT_URL) + Constants.GUGU_VIEW1_PICURL);
		article1.setDescription(Constants.GUGU_VIEW1_DESCRIPTION);
		article1.setUrl(Constants.GUGU_VIEW1_URL);
		//第二个图文
		Article article2 = new Article();
		article2.setTitle(Constants.GUGU_VIEW2_TITLE);
		article2.setPicUrl(requestMap.get(Constants.PROJECT_URL) + Constants.GUGU_VIEW2_PICURL);
		article2.setDescription(Constants.GUGU_VIEW2_DESCRIPTION);
		article2.setUrl(Constants.GUGU_VIEW2_URL);
		//第三个图文
		Article article3 = new Article();
		article3.setTitle(Constants.GUGU_VIEW3_TITLE);
		article3.setPicUrl(requestMap.get(Constants.PROJECT_URL) + Constants.GUGU_VIEW3_PICURL);
		article3.setDescription(Constants.GUGU_VIEW3_DESCRIPTION);
		article3.setUrl(Constants.GUGU_VIEW3_URL);
		
		
		articleList.add(article);
		articleList.add(article1);
		articleList.add(article2);
		articleList.add(article3);
		*/
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		returnMsg = WXParse.newsMessageToXml(newsMessage);
		log.info("processNewsMessage-->respMessage::"+returnMsg);
		return returnMsg;
	}
}

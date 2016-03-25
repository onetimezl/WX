package cn.com.bugu.weixin.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.bugu.weixin.beans.WXAuthenBean;
import cn.com.bugu.weixin.common.WXConfig;
import cn.com.bugu.weixin.common.WXConstant;
import cn.com.bugu.weixin.common.WXUtil;
import cn.com.bugu.weixin.untils.JSONUtil;

@Controller
@RequestMapping(value="/wxpa")
public class WXPlatformAuthenController {
	 private static Log log  = LogFactory.getLog(WXConstant.WX_LOG_NAME);
	 
	 @RequestMapping(value="/goTest")
	 public ModelAndView goTest(ModelAndView mav){
		 mav.setViewName("index");
		 
		 return mav;
	 }
	
	/**
	 * 微信平台身份认证
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/authen")
	public @ResponseBody String handleAuthen(
			WXAuthenBean authen,
			HttpServletRequest req,
			HttpServletResponse rsp,
			Model model) throws UnsupportedEncodingException{
		log.debug("WXPlatformAuthenController>>handleAuthen>>"+JSONUtil.beanToJson(authen));
		
		req.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		boolean isGet = req.getMethod().toLowerCase().equals("get");//是否是get请求
		String echostr = authen.getEchostr();
		//GET请求并且随机数不为空，说明是微信平台发送过来认证的请求
		if(isGet && StringUtils.isEmpty(echostr)){
			WXConfig wxcfg = WXConfig.getInstance();
			Map<String,String> wxcfgMap = wxcfg.getWXConfig();
			//检验验证请求
			boolean bln = WXUtil.checkSignature(wxcfgMap.get(WXConfig.MK.WX_TOKEN),authen.getSignature(), authen.getTimestamp(), authen.getNonce());
			if(bln){
				log.debug("checkSignature SUCCESS");
			}else{
				log.debug("checkSignature FAIL");
			}
		}else{
			//处理消息
		}
		return echostr;
	}
}

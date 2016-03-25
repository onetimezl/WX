package cn.com.bugu.weixin.page;


/**
 * 分页.
 * 
 */
public class Page {

	private int showCount; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private PageData pd = new PageData();

	public Page() {
		this.showCount = 10;
	}

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append("	<ul>\n");
			if (currentPage == 1) {
				sb.append("	<li style=\"margin:0 4px\"><a style=\'border:none;color:#000\'>共<font color=#000>" + totalResult
						+ "</font>条</a></li>\n");
				sb.append("	<li><input type=\"text\" value=\"\" id=\"toGoPage\" style=\"width:36px;text-align:center;float:left;margin:0 4px;font-size:12px\" placeholder=\"页码\" maxlength=\"9\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\"/></li>\n");
				sb.append("	<li class=\'hoverlia\' style=\"cursor:pointer;margin-right:4px\"><a onclick=\"toTZ();\" style=\"color:#666;background:none;border:none;margin-right:4px\">跳转</a></li>\n");
				sb.append("	<li class=\'hoverlia\' style=\"margin-left:8px\"><a style=\"cursor:pointer;margin-left:2px;border:none;color:#666\">首页</a></li>\n");
				sb.append("	<li class=\'hoverlia\'><a style=\'border:none;color:#666\'>上一页</a></li>\n");
			} else {
				sb.append("	<li style=\"margin:0 4px\"><a style=\'border:none;color:#000;\'>共<font color=#000>" + totalResult
						+ "</font>条</a></li>\n");
				sb.append("	<li><input type=\"text\" value=\"\" id=\"toGoPage\" style=\"width:36px;text-align:center;float:left;margin:0 4px;font-size:12px\" placeholder=\"页码\" maxlength=\"9\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\"/></li>\n");
				sb.append("	<li class=\'hoverlia\' style=\"cursor:pointer;margin-right:4px\"><a onclick=\"toTZ();\" style=\"color:#666;background:none;border:none;margin-right:4px\">跳转</a></li>\n");
				sb.append("	<li class=\'hoverlia\' style=\"cursor:pointer;margin-left:8px\"><a onclick=\"nextPage(1)\" style=\"cursor:pointer;margin-left:2px;border:none;color:#666\">首页</a></li>\n");
				sb.append("	<li class=\'hoverlia\' style=\"cursor:pointer;\"><a style=\'border:none;color:#666\' onclick=\"nextPage("
						+ (currentPage - 1) + ")\">上一页</a></li>\n");
			}
			int showTag = 5;// 分页标签显示数量
			int startTag = 1;
			if (currentPage > showTag) {
				startTag = currentPage - 1;
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i)
					sb.append("<li><a style=\'border:none\'><font style=\'color:#fff;background:#d32c3f;padding:5px 7px\'>" + i
							+ "</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a style=\'border:none;color:#000;padding:5px 7px\' onclick=\"nextPage("
							+ i + ")\">" + i + "</a></li>\n");
			}
			if (currentPage == totalPage) {
				sb.append("	<li class=\'hoverlia\'><a style=\'border:none;color:#666\'>下一页</a></li>\n");
				sb.append("	<li class=\'hoverlia\'><a style=\'border:none\'>尾页</a></li>\n");
			} else {
				sb.append("	<li class=\'hoverlia\' style=\"cursor:pointer;\"><a style=\'border:none;color:#666\' onclick=\"nextPage("
						+ (currentPage + 1) + ")\">下一页</a></li>\n");
				sb.append("	<li class=\'hoverlia\' style=\"cursor:pointer;\"><a style=\'border:none;color:#666\' onclick=\"nextPage("
						+ totalPage + ")\">尾页</a></li>\n");
			}
			//sb.append("	<li><a>第<span style=\"color:#ee3d3c\">" + currentPage + "</span>页</a></li>\n");
			sb.append("	<li><a style=\'border:none;color:#000;\'>共<span style=\"color:#000\">" + totalPage + "</span>页</a></li>\n");

			sb.append("	<li><select title='显示条数' style=\"width:55px;float:left;margin-left:4px\" onchange=\"changeCount(this.value)\">\n");
			sb.append("	<option value='" + showCount + "'>" + showCount
					+ "</option>\n");
			sb.append("	<option value='10'>10</option>\n");
			sb.append("	<option value='20'>20</option>\n");
			sb.append("	<option value='30'>30</option>\n");
			sb.append("	<option value='40'>40</option>\n");
			sb.append("	<option value='50'>50</option>\n");
			sb.append("	<option value='60'>60</option>\n");
			sb.append("	<option value='70'>70</option>\n");
			sb.append("	<option value='80'>80</option>\n");
			sb.append("	<option value='90'>90</option>\n");
			sb.append("	<option value='99'>99</option>\n");
			sb.append("	</select>\n");
			sb.append("	</li>\n");

			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");

			// 换页函数
			sb.append("function nextPage(page){");
			//sb.append(" top.jzts();");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		url = url + page + \"&"
					+ (entityOrField ? "showCount" : "page.showCount") + "="
					+ showCount + "\";\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		url = url + page + \"&"
					+ (entityOrField ? "showCount" : "page.showCount") + "="
					+ showCount + "\";\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 调整每页显示条数
			sb.append("function changeCount(value){");
			//sb.append(" top.jzts();");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		url = url + \"1&"
					+ (entityOrField ? "showCount" : "page.showCount")
					+ "=\"+value;\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"1&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		url = url + \"&"
					+ (entityOrField ? "showCount" : "page.showCount")
					+ "=\"+value;\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 跳转函数
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {

		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

}

package cn.com.bugu.weixin.rsp.vo;

public class ClickEvent extends EventMessage {
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}

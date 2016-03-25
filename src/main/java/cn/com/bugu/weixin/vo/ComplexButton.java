/**
 * comment：ComplexButton.java
 * data  HP
 * author:jianglq
 */

/**
 * comment：ComplexButton.java
 * data  HP
 * author 2015
 */
package cn.com.bugu.weixin.vo;

/**
 * 复杂按钮（父按钮）
 * @author jianglq
 * @time 下午04:15:59
 */
public class ComplexButton  extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] subButton) {
		sub_button = subButton;
	}

}

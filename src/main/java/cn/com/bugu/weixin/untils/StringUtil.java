package cn.com.bugu.weixin.untils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	/**
	 * 功能描述：判断字符串是否是Long
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str) {
		boolean flag = true;
		if (StringUtils.isNotEmpty(str) && StringUtils.isNumeric(str)) {
			try{
				Long.parseLong(str);
			}catch(Exception e){
				return false;
			}
		}
		return flag;
	}
	
	/**
	 * Unicode转UTF-8
	 * @param str
	 * @return
	 */
	public static String decodeUnicode(String str) {
		char aChar;
		int len = str.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = str.charAt(x++);
			if (aChar == '\\') {
				aChar = str.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = str.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}
	
	/**
	 * 功能描述：清除掉所有特殊字符 只允许字母和数字
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static  String StringFilter(String str) throws PatternSyntaxException      {      
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return   m.replaceAll("").trim();      
    }
	
	/**
	 * 是否是合法的手机号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[678])|(18[0-9])|(14[57]))[0-9]{8}$"); 
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
	}  
	
	/**
	 * 是否是合法的电话号码
	 * @param telephone
	 * @return
	 */
	public static boolean isTelephoneNO(String telephone){
		Pattern p=Pattern.compile("(0[0-9]{2,3}\\-?)([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?");
	    Matcher m=p.matcher(telephone);          
	    return m.matches();  
	}
	
	/**
	 * 判断字符串是否是包含字母数字，长度8-15位长
	 * @param pwd
	 * @return
	 */
	public static boolean isValidPassword(String pwd){
		Pattern p=Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$).{8,15}$");
	    Matcher m=p.matcher(pwd);
	    return m.matches();  
	}
}

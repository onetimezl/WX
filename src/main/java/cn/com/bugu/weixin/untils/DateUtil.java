package cn.com.bugu.weixin.untils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 时间工具类.
 * 
 * @author 蒋林秋
 * 
 * 
 * @version
 * 
 * @since 2015-7-22
 */
public class DateUtil {
private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * 功能描述：将日期转成字符串
	 *
	 * @param date
	 * @return
	 * 
	 * @author 龚太祥
	 *
	 * @since 2015-5-9
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String format(Date date){
		if(null==date){
			return "";
		}
		return format.format(date);
	}
	/**
	 * 
	 * 功能描述：获取当前的时间，精确到毫秒
	 *
	 * @return
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2015-6-3
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getNanoTime(){
		return System.nanoTime()+"";
	}
	
	/** 
	 * 
	 * 功能描述：将字符串转换成日期
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 * 
	 * @author 郝凯枫
	 *
	 * @since 2015-6-4
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date parse(String date) throws ParseException{
		if(date.isEmpty()){
			return null;
		}
		return format.parse(date);
	}
	
	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 功能描述：字符串转Long.
	 * 
	 * @param dateStr
	 * @return
	 * 
	 * @author 蒋林秋
	 * 
	 * @since 2015-7-28
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static long dateToLong(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
		try {
			return sdf.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static long getCurTime() {
		return new Date().getTime();
	}
	public static String getCurTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	/**
	 * 
	 * 功能描述：
	 * 
	 * @param dateStr
	 * @return
	 * 
	 * @author jianglq
	 * 
	 * @since 2015-8-13
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static long dateTimeToLong(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
		try {
			return sdf.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static String LongTodateTime(Long dateLong) {
		Date date = new Date(dateLong);
		return dateToString(date, "yyyy-MM-dd HH:mm:ss sss");
	}
	public static String getCurDate() {
		Date date = new Date();
		return dateToString(date, "yyyy-MM-dd");
	}
	public static String dateToString(Date data, String formatType) {
		return new SimpleDateFormat(formatType).format(data);
	}
	public static List<String> getMonthDays(){
		List<String> list=new ArrayList<String>();
		for (int i = 1; i <= 31; i++) {
			String day="";
			if (i<=9) {
				day="0";
			}
			list.add(day+i);
		}
		return list;
	}
	/**
	 * 
	 * 功能描述：Main().
	 * 
	 * @param args
	 * 
	 * @author 蒋林秋
	 * 
	 * @since 2015-8-1
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void main(String[] args) {
		// testTmallServicecenterTasksSearch();
		System.out.println(dateTimeToLong("2015-08-13 14:02:00 000")+"//"+"2015-08-13 14:02:00 000");
		System.out.println(getCurTime()+"//"+LongTodateTime(getCurTime()));
	}
}

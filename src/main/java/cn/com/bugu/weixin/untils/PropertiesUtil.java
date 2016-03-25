package cn.com.bugu.weixin.untils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesUtil {
	/**
	 * 读取properties的全部信息
	 * @return
	 */
	public static Properties readProperties(String filePath) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
				}
			}
		}
		return props;
	}
	
	/**
	 * @param filePath
	 * @return
	 */
	public static Map<String,String> readPropertiesToMap(String filePath) {
		Map<String,String> map = null;
		Properties props = readProperties(filePath);
		if(null!=props){
			map = propertiesToMap(props);
		}
		return map;
	}
	
	/**
	 * @param filePath
	 * @return
	 */
	public static Map<Integer,String> readPropertiesToMapInteger(String filePath) {
		Map<Integer,String> map = null;
		Properties props = readProperties(filePath);
		if(null!=props){
			map = PropertiesToMapInteger(props);
		}
		return map;
	}
	
	/**
	 * 写入properties信息
	 * @param filePath
	 * @param parameterName
	 * @param parameterValue
	 */
    public static void writeProperties(String filePath,String parameterName,String parameterValue) {
        Properties prop = new Properties();
        InputStream fis = null;
        OutputStream fos = null;
        try {
        	fis = new FileInputStream(filePath);
        	fos = new FileOutputStream(filePath);
            //从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            prop.setProperty(parameterName, parameterValue);
            prop.store(fos, "Update/Insert [" + parameterName + "] value");
        } catch (IOException e) {
            System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
        }finally{
        	try {
        		if(fis!=null){
        			fis.close();
        			fis=null;
        		}
	        	if(fos!=null){
					fos.close();
					fos=null;
	        	}
        	} catch (Exception e2) {
        		e2.printStackTrace();
        	}
        }
    }
    
    /**
	 * 写入properties信息
	 * @param filePath
	 * @param parameterName
	 * @param parameterValue
	 */
    public static void writeProperties(String filePath,Properties p) {
        Properties prop = new Properties();
        prop = p;
        OutputStream fos = null;
        try {
        	fos = new FileOutputStream(filePath);
            //从输入流中读取属性列表（键和元素对）
            prop.store(fos, "Update/Insert [" + filePath + "] value");
        } catch (IOException e) {
            System.err.println("Visit "+filePath+" for updating value error");
        }finally{
        	if(fos!=null){
        		try {
					fos.close();
					fos=null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
        	}
        }
    }
    
    /**
     * Properties转Map<String,String>
     * @param p
     * @return
     */
    public static Map<String,String> propertiesToMap(Properties p){
    	Map<String,String> map = new HashMap<String,String>();
    	for(Entry<Object,Object> e : p.entrySet()){
    		map.put(e.getKey().toString(), e.getValue().toString());
    	}
    	return map;
    }
    
    /**
     * Properties转Map<Integer,String>
     * @param p
     * @return
     */
    public static Map<Integer,String> PropertiesToMapInteger(Properties p){
    	Map<Integer,String> map = new HashMap<Integer,String>();
    	for(Entry<Object,Object> e : p.entrySet()){
    		map.put(Integer.valueOf(e.getKey().toString()), e.getValue().toString());
    	}
    	return map;
    }
}


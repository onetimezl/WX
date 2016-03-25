package cn.com.onetime.demo;

import java.math.BigInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;

public class TestDemo1 {
	public static void main(String[] args) {
		BigInteger bi = new BigInteger("0");
		bi = bi.setBit(2);
		bi = bi.setBit(3);
//		System.out.println(bi.testBit(3));
//		bi = bi.clearBit(3);
//		System.out.println(bi.testBit(5));
//		
//		System.out.println(bi.shiftRight(2));
		System.out.println(bi.intValue());
		
//		System.out.println(bi.signum());
	}
	
	public void testShiro(){
		//IniSecurityManagerFactory factory1 = new IniSecurityManagerFactory("classpath:user.ini");
		Factory<org.apache.shiro.mgt.SecurityManager> factory =  new IniSecurityManagerFactory("classpath:user/user.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
	    
	    try{
	    	subject.login(token);
	    }catch(AuthenticationException e){
	    	System.out.println("身份认证失败");
	    }
	    
	    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
	    System.out.println("身份认证成功");
	    subject.logout();
	}
}

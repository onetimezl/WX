package cn.com.bugu.weixin.demo1;

import org.junit.Assert;

public class HelloWorldTest {
	public void testSayHello(){
		Assert.assertEquals("Hello World", new HelloWorld().sayHello());
	}
}

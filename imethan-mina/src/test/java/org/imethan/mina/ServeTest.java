package org.imethan.mina;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ServeTest.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2016年3月16日下午4:06:01
 */
public class ServeTest {
	
	public static void main(String[] args) {
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("services.xml");
	    BeanTest beanTest = ctx.getBean(BeanTest.class);
	    System.out.println(beanTest.getName());
	}

}

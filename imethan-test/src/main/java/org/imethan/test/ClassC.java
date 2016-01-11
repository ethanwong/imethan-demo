package org.imethan.test;

/**
 * ClassB.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月15日下午5:30:56
 */
public class ClassC extends ClassA {
	
	public static void main(String[] args) {
		ClassA p0 = new ClassA(); 
		ClassB p1 = new ClassB(); 
		ClassC p2 = new ClassC();
		ClassA p3 = new ClassB(); 
		ClassA p4 = new ClassC(); 
		
//		p0 = p1;
//		p1 =p2;
//		p2 = (ClassC) p4;
//		 p2 = (ClassC)p1;
		 p1 = (ClassB)p3;
	}

}

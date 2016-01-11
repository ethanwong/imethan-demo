package org.imethan.test;

/**
 * Test.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月14日下午5:22:15
 */
public abstract class Test {
	
	public enum Dogs{collie,harrier}
	
	public static void main(String[] args) {
		Dogs myDog = Dogs.collie;
		switch (myDog) {
		case collie:
			System.out.println("collie");
			break;
		case harrier:
			System.out.println("harrier");
			break;

		}
	}
	
	public abstract  void test1();



}

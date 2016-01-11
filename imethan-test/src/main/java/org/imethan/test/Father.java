package org.imethan.test;

/**
 * Father.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月15日下午4:47:52
 */
public class Father {
	
	public static void go(double n) {
		System.out.print("Double ");
	}   
	public static void go(Long n) {
	    System.out.print("Long ");
	  }   
	  public static void go(short n) {
	    System.out.print("Short ");
	  }   
	  public static void go(int n) {
	    System.out.print("int ");
	  }   
	  public static void main(String[] args) { 
	    short y = 6;
	    long z = 7; 
	    go(y);
	    go(z); 
	  } 

}

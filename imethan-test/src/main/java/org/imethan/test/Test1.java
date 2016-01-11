package org.imethan.test;

/**
 * Test1.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月15日下午5:40:50
 */
public class Test1 extends Thread {
	  private static int x;
	  public synchronized void doThings() {
	    int current = x;
	    current++;
	    x = current;
	  }
	  public synchronized  void run() {
	    doThings();
	  }
	  
	  public static void main(String[] args) {
		  new Test1().start();
	}

}

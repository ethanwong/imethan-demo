package org.imethan.test;

/**
 * Base.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月14日下午6:11:56
 */
public class Base {
	public static final String FOO = "foo"; 
	
	  public static void main(String[] args) {
		    Base b = new Base();
		    Sub s = new Sub(); 
		    System.out.print(Base.FOO); //  foo
		    System.out.print(Sub.FOO);    //bar 
		    System.out.print(b.FOO);     //foo
		    System.out.print(s.FOO); //bar
		    System.out.print(((Base)s).FOO); //bar  
		}
}

class Sub extends Base {
	   public static final String FOO="bar";
	} 
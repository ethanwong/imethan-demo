package org.imethan.test;

/**
 * Employee.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月14日下午5:36:28
 */
public class Employee extends Person {

	String name = "no name";
	

	public Employee(String nm) {
		super(nm);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		new Employee("11");
	}
	
	void go() { 
	    synchronized(Object.class) { /* code here */ }   } 

}

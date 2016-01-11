package cn.imethan.web.struts2;

/**
 * Person.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月31日下午2:19:09
 */
public class Person {
	
	private String firstName;
	private String lastName;
	private String email;
	private int age;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "First Name: " + getFirstName() + " Last Name:  " + getLastName() + " Email:      " + getEmail() + " Age:      " + getAge();
	}
}

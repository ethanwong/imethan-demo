package cn.imethan.webflow;

import java.io.Serializable;

/**
 * Product.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月9日上午9:53:03
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 5695698589213391045L;
	private Integer id;
	private String name;
	private Integer price;

	public Product(Integer id, String name, Integer price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}

package cn.imethan.webflow;

import java.io.Serializable;

/**
 * CartItem.java 购物车中的条目
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月9日上午10:14:21
 */
public class CartItem implements Serializable {
	private static final long serialVersionUID = 8388627124326126637L;
	private Product product;// 商品
	private int quantity;// 数量

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	// 计算该条目的总价格
	public int getTotalPrice() {
		return this.quantity * this.product.getPrice();
	}

	// 增加商品的数量
	public void increaseQuantity() {
		this.quantity++;
	}

	/**
	 * Return property product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets property product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Return property quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets property quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/* getter setter */

}

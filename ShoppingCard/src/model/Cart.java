package model;

import java.util.HashSet;
import java.util.Set;

public class Cart{
	private Set<CartItem> products = new HashSet<>();
	private Coupon coupon = null;
	
	public Cart() {
		super();
	}
	public class CartItem {
		public Product product;
		public int quantity;
		public CartItem(Product product, int quantity) {
			super();
			this.product = product;
			this.quantity = quantity;
		}
	}
	public Set<CartItem> getProducts() {
		return products;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon cartCoupon) {
		this.coupon = cartCoupon;
	}

	public void addToCart(Product product, int quantity) {
		products.add(new CartItem(product, quantity));
	}
}

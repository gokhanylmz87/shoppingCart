package model;

public class Coupon {
	private String couponName;
	private double minCartAmount;
	private double discountRate;

	public Coupon(String couponName, double minCartAmount, double discountRate) {
		super();
		this.couponName = couponName;
		this.minCartAmount = minCartAmount;
		this.discountRate = discountRate;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public double getMinCartAmount() {
		return minCartAmount;
	}
	public void setMinCartAmount(double minCartAmount) {
		this.minCartAmount = minCartAmount;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
}

package control;

import model.Cart;

public interface ICartControl {
	Cart createShoppingCart();
	double calculateCartCost();
	boolean isCouponApplicable();
}

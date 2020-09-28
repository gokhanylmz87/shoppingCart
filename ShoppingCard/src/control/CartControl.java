package control;

import java.util.concurrent.atomic.AtomicReference;

import model.Cart;
import model.Cart.CartItem;

public class CartControl implements ICartControl {
	private int TOTALRATE = 100;
	private Cart shoppingCart = null;

	@Override
	public double calculateCartCost() {
		AtomicReference<Double> cartCost = new AtomicReference<>();
		cartCost.set(new Double(0));

		if (null != shoppingCart) {
			shoppingCart.getProducts().forEach(cartItem -> {
				double itemCost = cartItem.product.getPrice() * cartItem.quantity;
				if(null != cartItem.product.getCategory().getCampaign()) {
					AtomicReference<Double> discountRateRef = new AtomicReference<>();
					discountRateRef.set(new Double(0));
					AtomicReference<CartItem> cartItemRef = new AtomicReference<>();
					cartItemRef.set(cartItem);
					cartItem.product.getCategory().getCampaign().getQuantityDiscountRateMap().entrySet().forEach(entry -> {
						if(entry.getKey() <= cartItemRef.get().quantity) {
							discountRateRef.set(entry.getValue());
						}
					});
					itemCost = itemCost * (TOTALRATE - discountRateRef.get()) / TOTALRATE;
				} 				
				cartCost.set(cartCost.get() + itemCost);
			});
		}

		return cartCost.get();
	}

	@Override
	public boolean isCouponApplicable() {
		boolean isApplicable = false;
		if (null != shoppingCart.getCoupon()) {
			if (calculateCartCost() >= shoppingCart.getCoupon().getMinCartAmount()) {
				isApplicable = true;
			}
		}
		return isApplicable;
	}

	@Override
	public Cart createShoppingCart() {
		shoppingCart = new Cart();
		return shoppingCart;
	}

}

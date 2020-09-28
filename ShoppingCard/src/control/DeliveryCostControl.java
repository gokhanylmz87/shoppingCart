package control;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import factory.ApplicationFactory;
import model.Cart;

public class DeliveryCostControl implements IDeliveryCostControl{
	private int TOTALRATE = 100;	
	
	public double calculateDeliveryCost(Set<Cart> delivery) {
		AtomicReference<Double> deliveryCost = new AtomicReference<>();
		deliveryCost.set(new Double(0));
		
		delivery.forEach(cart -> {
			double cartCost = ApplicationFactory.createCartControl().calculateCartCost();
			if(ApplicationFactory.createCartControl().isCouponApplicable()) {
				cartCost = applyCoupon (cartCost, cart.getCoupon().getDiscountRate()); 
			}
			deliveryCost.set(deliveryCost.get() + cartCost);
		});
		
		return deliveryCost.get();
	}

	@Override
	public double calculateTotalCost(Set<HashSet<Cart>> deliveryList) {
		AtomicReference<Double> totalCost = new AtomicReference<>();
		totalCost.set(new Double(0));
		
		deliveryList.forEach(delivery -> {
			double deliveryCost = calculateDeliveryCost(delivery);
			totalCost.set(totalCost.get() + deliveryCost);
		});
		return totalCost.get();
	}
	
	@Override
	public double applyCoupon(double cartCost, double couponRate) {
		return cartCost * (TOTALRATE - couponRate) / TOTALRATE;
	}
}

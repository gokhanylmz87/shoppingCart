package control;

import java.util.HashSet;
import java.util.Set;

import model.Cart;

public interface IDeliveryCostControl {
//	double calculateDeliveryCost(Set<Cart> delivery);
	double calculateTotalCost(Set<HashSet<Cart>> deliveryList);
	double applyCoupon(double cartCost, double couponRate);
}

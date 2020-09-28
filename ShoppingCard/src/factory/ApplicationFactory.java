package factory;

import control.CartControl;
import control.DeliveryCostControl;
import control.ICartControl;
import control.IDeliveryCostControl;

public class ApplicationFactory {
	private static ICartControl cartControl;
	private static IDeliveryCostControl deliveryCostControl;
	
	public ApplicationFactory() {
		super();
	}
	public static ICartControl createCartControl() {
		if (null == cartControl) {
			cartControl = new CartControl();
		}
		return cartControl;
	}
	
	public static IDeliveryCostControl createDeliveryCostControl() {
		if (null == deliveryCostControl) {
			deliveryCostControl = new DeliveryCostControl();
		}
		return deliveryCostControl;
	}
}

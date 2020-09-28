package unitTest;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;
import factory.ApplicationFactory;
import model.Campaign;
import model.Cart;
import model.Category;
import model.Coupon;
import model.Product;

public class TestShoppingCart {
	@Test
	public void testSimpleShoppingCart() {
		// sample creating a new category
		Category food = new Category("food");
		// products
		Product apple = new Product("Apple", 100.0, food);
		Product almond = new Product("Almonds", 150.0, food);
		// create a shopping cart and add products
		ApplicationFactory myFactory = new ApplicationFactory();
		Cart myShoppingCart = ApplicationFactory.createCartControl().createShoppingCart();
		myShoppingCart.addToCart(apple, 10);
		myShoppingCart.addToCart(almond, 50);
		// create delivery
		HashSet<Cart> delivery = new HashSet<>();
		delivery.add(myShoppingCart);
		Set<HashSet<Cart>> deliveryList = new HashSet<>();
		deliveryList.add(delivery);
		double deliveryTotalCost = ApplicationFactory.createDeliveryCostControl().calculateTotalCost(deliveryList);
		assertEquals(8500.0, deliveryTotalCost, 0.0);
	}
	@Test
	public void testShoppingCartWithCampaign() {
		// sample creating a new category
		Category food = new Category("food");
		SortedMap<Integer, Double> quantityDiscountRateMap = new TreeMap<>();
		quantityDiscountRateMap.put(new Integer(25), new Double(50));
		quantityDiscountRateMap.put(new Integer(25), new Double(75));
		food.setTitle("Food");
		Category market = new Category("Market");
		food.setParentCategory(market);
		System.out.println("Category:" + food.getTitle());
		System.out.println("Parent Category: " + food.getParentCategory().getTitle());
		Campaign appleCampaign = new Campaign(quantityDiscountRateMap);
		appleCampaign.setQuantityDiscountRateMap(quantityDiscountRateMap);
		food.setCampaign(appleCampaign);
		// products
		Product apple = new Product();
		apple.setTitle("Apple");
		apple.setPrice(100);
		apple.setCategory(food);
		Product almond = new Product("Almonds", 150.0, food);
		System.out.println("Products: " + apple.getTitle() + "-" + almond.getTitle());
		// create a shopping cart and add products
		Cart myShoppingCart = ApplicationFactory.createCartControl().createShoppingCart();
		myShoppingCart.addToCart(apple, 10);
		myShoppingCart.addToCart(almond, 50);
		// create delivery
		HashSet<Cart> delivery = new HashSet<>();
		delivery.add(myShoppingCart);
		Set<HashSet<Cart>> deliveryList = new HashSet<>();
		deliveryList.add(delivery);
		double deliveryTotalCost = ApplicationFactory.createDeliveryCostControl().calculateTotalCost(deliveryList);
		assertEquals(2875.0, deliveryTotalCost, 0.0);
	}
	@Test
	public void testShoppingCartWithCoupon() {
		// sample creating a new category
		Category food = new Category("food");
		SortedMap<Integer, Double> quantityDiscountRateMap = new TreeMap<>();
		quantityDiscountRateMap.put(new Integer(25), new Double(50));
		Campaign appleCampaign = new Campaign(quantityDiscountRateMap);
		food.setCampaign(appleCampaign);
		// products
		Product apple = new Product("Apple", 100.0, food);
		Product almond = new Product("Almonds", 150.0, food);
		// create a shopping cart and add products
		Cart myShoppingCart = ApplicationFactory.createCartControl().createShoppingCart();
		Coupon cartCoupon = new Coupon("FirstShopping", 90.0, 9.0);
		cartCoupon.setCouponName("First Shopping Coupon");
		cartCoupon.setMinCartAmount(1500);
		cartCoupon.setDiscountRate(10);
		System.out.println("Coupon Name: " + cartCoupon.getCouponName());
		myShoppingCart.setCoupon(cartCoupon);
		myShoppingCart.addToCart(apple, 10);
		myShoppingCart.addToCart(almond, 50);
		//create delivery
		HashSet<Cart> delivery = new HashSet<>();
		delivery.add(myShoppingCart);
		Set<HashSet<Cart>> deliveryList = new HashSet<>();
		deliveryList.add(delivery);
		double deliveryTotalCost = ApplicationFactory.createDeliveryCostControl().calculateTotalCost(deliveryList);
		assertEquals(4275.0, deliveryTotalCost, 0.0);
	}
	
}

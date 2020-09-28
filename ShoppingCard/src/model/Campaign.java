package model;

import java.util.SortedMap;
import java.util.TreeMap;

public class Campaign {
	private SortedMap<Integer, Double> quantityDiscountRateMap = new TreeMap<>();
	
	public Campaign(SortedMap<Integer, Double> quantityDiscountRateMap) {
		super();
		this.quantityDiscountRateMap = quantityDiscountRateMap;
	}

	public SortedMap<Integer, Double> getQuantityDiscountRateMap() {
		return quantityDiscountRateMap;
	}
	
	public void setQuantityDiscountRateMap(SortedMap<Integer, Double> quantityDiscountRateMap) {
		this.quantityDiscountRateMap = quantityDiscountRateMap;
	}
}

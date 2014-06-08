package ninja.epsilon.drinkers;

import java.util.List;

public interface Drinker {
	
	public enum DrinkerType {
	    SPANISH, 
	    GERMAN, 
	    NORWEGIAN,
	    POLISH,
	    ROMANIAN 
	}
	
	/*
	 * Getter for the Drinker Type
	 */
	DrinkerType GetDrinkerType();

	/**
	 * Return drinker position along bar.
	 * @return
	 */
	float getPosition();
	
	/*
	 * Return Drink Orders
	 */
	List<? extends DrinkOrder> getDrinkOrders();
}

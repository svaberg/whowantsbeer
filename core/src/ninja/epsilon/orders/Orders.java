package ninja.epsilon.orders;

import ninja.epsilon.drinkers.DrinkOrder;

public interface Orders {
	void addOrder(DrinkOrder order);
	void removeOrder(DrinkOrder order);
}

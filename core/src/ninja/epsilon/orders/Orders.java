package ninja.epsilon.orders;

import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;

public interface Orders {
	void addOrder(DrinkOrder order);
	void removeOrder(DrinkOrder order);
	int matchDrink(int position, TypeOfDrink typeOfDrink, long timeOfReceivingDrink);
	void removeAllOrdersAtPosition(int position);
}

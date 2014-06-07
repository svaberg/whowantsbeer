package ninja.epsilon.orders;

import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;

public interface Orders {
	void addOrder(DrinkOrder order);
	void removeOrder(DrinkOrder order);
	int matchDrink(float position, TypeOfDrink typeOfDrink, long timeOfReceivingDrink);
	void removeAllOrdersAtPosition(float position);
}

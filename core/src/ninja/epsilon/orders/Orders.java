package ninja.epsilon.orders;

import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;

public interface Orders {
	void addOrder(DrinkOrder order);
	void removeOrder(DrinkOrder order);
	void matchBeer(int position, TypeOfDrink typeOfDrink)
}

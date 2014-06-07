package ninja.epsilon.drinkers;

public class DrinkOrder {

	Drink drink;
	boolean isReceived;
	TypeOfDrink whatDoesHeDrink;
	int position = 0;
	
	DrinkOrder(TypeOfDrink typeOfDrink, int drinkerPosition)
	{
		drink = new Drink();
		isReceived = false;
		whatDoesHeDrink = typeOfDrink;
		position = drinkerPosition;
	}
	
	void setReceived() {
		isReceived = true;
	}
	
	boolean isReceived() {
		return isReceived;
	}
}

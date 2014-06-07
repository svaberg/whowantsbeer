package ninja.epsilon.drinkers;

public class GenericDrinkOrder {

	GenericDrink drink;
	boolean isReceived;
	TypeOfDrink whatDoesHeDrink;
	int position = 0;

	GenericDrinkOrder(TypeOfDrink typeOfDrink, int drinkerPosition)
	{
		drink = new GenericDrink();
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

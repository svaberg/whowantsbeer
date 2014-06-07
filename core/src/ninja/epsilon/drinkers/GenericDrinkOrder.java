package ninja.epsilon.drinkers;

public class GenericDrinkOrder {

	GenericDrink drink;
	boolean isReceived;
	
	GenericDrinkOrder()
	{
		drink = new GenericDrink();
		isReceived = false;
	}
	
	void setReceived() {
		isReceived = true;
	}
	
	boolean isReceived() {
		return isReceived;
	}
}

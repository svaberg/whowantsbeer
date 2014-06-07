package ninja.epsilon.drinkers;

public class DrinkOrder {

	Drink drink;
	boolean isReceived;
	
	DrinkOrder()
	{
		drink = new Drink();
		isReceived = false;
	}
	
	void setReceived() {
		isReceived = true;
	}
	
	boolean isReceived() {
		return isReceived;
	}
}

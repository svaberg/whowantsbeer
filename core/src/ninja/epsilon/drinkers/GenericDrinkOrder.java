package ninja.epsilon.drinkers;

public class GenericDrinkOrder implements DrinkOrder{

	GenericDrink drink;
	boolean isReceived;
	TypeOfDrink whatsTheDrink;
	int position = 0;
	long orderTime = 0;
	
	GenericDrinkOrder(TypeOfDrink typeOfDrink, int drinkerPosition, long orderTime)
	{
		isReceived = false;
		whatsTheDrink = typeOfDrink;
		drink = new GenericDrink(whatsTheDrink);
		position = drinkerPosition;
		this.orderTime = orderTime;
	}
	
	void setReceived() {
		isReceived = true;
	}
	
	boolean isReceived() {
		return isReceived;
	}


	public TypeOfDrink getWhatsTheDrink() {
		return whatsTheDrink;
	}

	public int getPosition() {
		return position;
	}

	public int getPointsReceived() {
		// TODO Auto-generated method stub
		return 0;
	}
}

package ninja.epsilon.drinkers;

import ninja.epsilon.score.ScoringValues;

public class GenericDrinkOrder implements DrinkOrder{

	GenericDrink drink;
	boolean isReceived;
	TypeOfDrink whatsTheDrink;
	float position = 0;
	long orderTime = 0;
	
	GenericDrinkOrder(TypeOfDrink typeOfDrink, float drinkerPosition, long orderTime)
	{
		isReceived = false;
		whatsTheDrink = typeOfDrink;
		drink = new GenericDrink(whatsTheDrink);
		position = drinkerPosition;
		this.orderTime = orderTime;
	}
	
	public void setReceived() {
		isReceived = true;
	}
	
	boolean isReceived() {
		return isReceived;
	}

	public TypeOfDrink getWhatsTheDrink() {
		return whatsTheDrink;
	}

	public float getPosition() {
		return position;
	}

	public int getPointsReceived(long timeOfReceivingDrink) {
		long diff = timeOfReceivingDrink - orderTime;
		
		ScoringValues sv= new ScoringValues();
		
		return 0;
	}


}

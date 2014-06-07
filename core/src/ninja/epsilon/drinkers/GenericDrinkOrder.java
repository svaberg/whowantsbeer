package ninja.epsilon.drinkers;

import ninja.epsilon.score.ScoringValues;

public class GenericDrinkOrder implements DrinkOrder{
	boolean isReceived;
	TypeOfDrink whatsTheDrink;
	int position = 0;
	long orderTime = 0;
	
	GenericDrinkOrder(TypeOfDrink typeOfDrink, int drinkerPosition, long orderTime)
	{
		isReceived = false;
		whatsTheDrink = typeOfDrink;
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

	public int getPosition() {
		return position;
	}

	public int getPointsReceived(long timeOfReceivingDrink) {
		int pointsGot = ScoringValues.calculatePoints(timeOfReceivingDrink - orderTime); 
		return pointsGot;

	}


}

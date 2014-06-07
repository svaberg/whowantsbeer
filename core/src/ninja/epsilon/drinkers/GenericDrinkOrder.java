package ninja.epsilon.drinkers;

import ninja.epsilon.score.ScoringValues;

public class GenericDrinkOrder implements DrinkOrder{

	GenericDrink drink;
	boolean isReceived;
	TypeOfDrink whatsTheDrink;
	int position = 0;
	long timeWhenReceived = 0;
	
	GenericDrinkOrder(TypeOfDrink typeOfDrink, int drinkerPosition, long receivedTime)
	{
		drink = new GenericDrink();
		isReceived = false;
		whatsTheDrink = typeOfDrink;
		position = drinkerPosition;
		timeWhenReceived = receivedTime;
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
		long diff = timeOfReceivingDrink - timeWhenReceived;
		
		ScoringValues sv= new ScoringValues();
		
		return 0;
	}


}

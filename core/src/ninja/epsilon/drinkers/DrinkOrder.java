package ninja.epsilon.drinkers;

public interface DrinkOrder {
	TypeOfDrink getWhatsTheDrink();
	float getPosition();
	int getPointsReceived(long timeOfReceivingDrink);
	void setReceived();
	boolean isReceived();
}

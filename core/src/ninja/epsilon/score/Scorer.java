package ninja.epsilon.score;

import ninja.epsilon.GameLevel;
import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;

public interface Scorer {
	/**
	 * Returns the current level of the game.
	 */
	GameLevel getGameLevel();
	
	//Drink will be matched will orders -> leads to increment in score or penalty
	void gotOneDrink(TypeOfDrink typeOfDrink, int position, long timeOfReceivingDrink);
	
	//Order will be added to the orders
	void gotOneOrder(DrinkOrder order);
	
	//Order will be removed
	void removeOrder(DrinkOrder order);
	
	//All Orders at that position will be removed
	void drinkerLeft(int position);
	
	// Returns the current score of the game.
	int getScore();
}

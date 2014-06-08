package ninja.epsilon.score;

import ninja.epsilon.GameLevel;
import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.drinkers.TypeOfDrink;

public interface Scorer {
	/**
	 * Returns the current level of the game.
	 */
	GameLevel getGameLevel();
	
	// Drink will be matched will orders -> leads to increment in score or penalty
	void gotOneDrink(TypeOfDrink typeOfDrink, float position, long timeOfReceivingDrink);
	
	// Drink fell off the counter
	void fellOneDrink();
	
	// Returns the current score of the game.
	int getScore();
	
	// Returns how many times can the gamer still fail before game over
	int getChances();
	
	// Returns how many times the gamer failed
	int getNrOfFails();
	
	boolean gameOver();

	//should be set in the main loop
	void setDrinkers(Drinkers drinkers);


}

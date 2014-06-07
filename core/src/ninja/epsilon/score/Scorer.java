package ninja.epsilon.score;

import ninja.epsilon.GameLevel;
import ninja.epsilon.drinkers.TypeOfDrink;

public interface Scorer {
	/**
	 * Returns the current level of the game.
	 */
	GameLevel getGameLevel();
	
	void GotOneDrink(TypeOfDrink typeOfDrink, int position);
}

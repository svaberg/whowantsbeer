package ninja.epsilon.drinkers;

import java.util.List;
import ninja.epsilon.GameLevel;

public interface Drinkers {
	/**
	 * Hook for the drinkers called from the main loop.
	 * This will create new drinkers, retire old ones, place orders
	 * into the order book etc.
	 */
	void update(long curTime, GameLevel level);
	
	/*
	 * Getter for the Drinkers List
	 */
	List<? extends Drinker> GetDrinkers();

	int giveDrink(TypeOfDrink typeOfDrink, float position,
			long timeOfReceivingDrink);

}

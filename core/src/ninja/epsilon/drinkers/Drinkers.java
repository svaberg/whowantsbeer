package ninja.epsilon.drinkers;

import java.util.List;

public interface Drinkers {
	/**
	 * Hook for the drinkers called from the main loop.
	 * This will create new drinkers, retire old ones, place orders
	 * into the order book etc.
	 */
	void update();
	
	/*
	 * Getter for the Drinkers List
	 */
	List<Drinker> GetDrinkers();
}

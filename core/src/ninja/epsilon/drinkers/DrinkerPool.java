package ninja.epsilon.drinkers;

import java.util.List;

public class DrinkerPool implements Drinkers{

	/**
	 * List of drinkers currently at the bar
	 */
	List<Drinker> drinkersWaiting;
	
	/**
	 * Create a new drinker
	 */
	private void createDrinker() {
		Drinker drinker = new Drinker();
		drinkersWaiting.add(drinker);
	}
	
	/** 
	 * Purge the list of drinkers who have received all their orders.
	 */
	private void purge() {
		for (Drinker drinker : drinkersWaiting) {
			
		}
	}
	public void update() {
		
	}
	
	
}

package ninja.epsilon.drinkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ninja.epsilon.GameLevel;

public class DrinkerPool implements Drinkers{

	/**
	 * List of drinkers currently at the bar
	 */
	List<GenericDrinker> drinkersWaiting;
	
	/**
	 * Mean time between drinkers appearing at the bar
	 */
	long meanTimeBetweenDrinkers;
	
	/** 
	 * Previous time the drinkers pool was updated
	 */
	long previousUpdateTime;
	
	/**
	 * Current time the drinkers pool is updated
	 */
	long currentUpdateTime;
	
	public DrinkerPool() {
		drinkersWaiting = new ArrayList<GenericDrinker>();
		meanTimeBetweenDrinkers = 3000; // milliseconds
		previousUpdateTime = 0;
		currentUpdateTime = 0;
	}
	
	/**
	 * Create a new drinker
	 */
	private void createDrinker() {
		GenericDrinker drinker = new GenericDrinker(currentUpdateTime);
		drinkersWaiting.add(drinker);
	}
	
	/** 
	 * Purge the list of drinkers who have received all their orders.
	 */
	private void updateDrinkers() {
		// Create list of leavers
		List<GenericDrinker> leavers = new ArrayList<GenericDrinker>();
		for (GenericDrinker drinker : drinkersWaiting) {
			drinker.update(currentUpdateTime);
			if (drinker.hasLeft()) {
				leavers.add(drinker);
			}
		}
		
		// Remove leavers from drinker pool.
		drinkersWaiting.removeAll(leavers);		
	}
	
	/**
	 * Update drinker pool.
	 * @param nowTime the current time in milliseconds.
	 */
	public void update(long nowTime, GameLevel gameLevel) {
		// Update the time information first
		previousUpdateTime = currentUpdateTime;
		currentUpdateTime = nowTime;
		
		updateInternal();
	}
	
	private void updateInternal() {

		// Remove drinkers
		updateDrinkers();

		// Add a new drinker if needed
		Random ran = new Random();
		long randomTime = ran.nextLong() % meanTimeBetweenDrinkers;
		
		// Make this more fancy later (Poisson process).
		if (currentUpdateTime - previousUpdateTime > randomTime) {
			createDrinker();
		}
	}
	
	
}

package ninja.epsilon.drinkers;

import java.util.ArrayList;
import java.util.List;

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
	 * Last time the drinkers pool was updated
	 */
	long lastUpdateTime;
	
	/**
	 * Create a new drinker
	 */
	private void createDrinker() {
		GenericDrinker drinker = new GenericDrinker(lastUpdateTime);
		drinkersWaiting.add(drinker);
	}
	
	/** 
	 * Purge the list of drinkers who have received all their orders.
	 */
	private void purge() {
		// Create list of leavers
		List<GenericDrinker> leavers = new ArrayList<GenericDrinker>();
		for (GenericDrinker drinker : drinkersWaiting) {
			if (drinker.hasLeft(lastUpdateTime)) {
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
		lastUpdateTime = nowTime;
		
		purge();

		// Make this more fancy later (Poisson process).
		if (nowTime % meanTimeBetweenDrinkers == 0) {
			createDrinker();
		}
	}

	@Override
	public List<? extends Drinker> GetDrinkers() {
		// TODO Auto-generated method stub
		return drinkersWaiting;
	}
	
	
}

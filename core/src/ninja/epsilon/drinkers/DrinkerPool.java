package ninja.epsilon.drinkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ninja.epsilon.GameLevel;

import com.badlogic.gdx.Gdx;

public class DrinkerPool implements Drinkers{

	/**
	 * List of drinkers currently at the bar
	 */
	List<GenericDrinker> drinkersWaiting;
	
	/**
	 * Capacity of bar to host drinkers at the same time.
	 */
	long capacity;
	
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
	
	/**
	 * Length of counter
	 */
	
	public DrinkerPool() {
		drinkersWaiting = new ArrayList<GenericDrinker>();
		meanTimeBetweenDrinkers = 3000; // milliseconds
		capacity = 3;
		previousUpdateTime = 0;
		currentUpdateTime = 0;
	}
	
	/**
	 * Create a new drinker
	 */
	private void createDrinker() {
		GenericDrinker drinker = new GenericDrinker(currentUpdateTime);
		drinkersWaiting.add(drinker);
		Gdx.app.log("MyTag", "Created new drinker. Size of drinker list: " + drinkersWaiting.size());
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
				Gdx.app.log("MyTag", "Drinker has left. Size of drinker list: " + drinkersWaiting.size());
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

//		Gdx.app.log("MyTag", "Update time: " + currentUpdateTime);

		updateInternal();
	}
	
	private void updateInternal() {

		// Remove drinkers
		updateDrinkers();

		// Add a new drinker if needed
		if (drinkersWaiting.size() < capacity)
		{
			Random ran = new Random();
			long randomTime = ran.nextLong() % meanTimeBetweenDrinkers;
			
			// Make this more fancy later (Poisson process).
			if (currentUpdateTime - previousUpdateTime < randomTime) {
				createDrinker();
			}
		}
	}
	
	@Override
	public List<? extends Drinker> GetDrinkers() {
		// TODO Auto-generated method stub
		return drinkersWaiting;
	}
	
	
}

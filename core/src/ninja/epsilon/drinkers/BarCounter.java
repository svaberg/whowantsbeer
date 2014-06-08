package ninja.epsilon.drinkers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ninja.epsilon.Dimensions;
import ninja.epsilon.GameLevel;
import ninja.epsilon.score.Scorer;
import ninja.epsilon.score.ScoringValues;

import com.badlogic.gdx.Gdx;

public class BarCounter implements Drinkers{

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
	float length;
	
	Scorer score;
	
	/**
	 * Create bar counter object
	 */
	public BarCounter(Scorer score) {
		drinkersWaiting = new ArrayList<GenericDrinker>();
		meanTimeBetweenDrinkers = Dimensions.BAR_MEAN_TIME_BETWEEN_DRINKERS;
		capacity = Dimensions.BAR_COUNTER_CAPACITY;
		previousUpdateTime = 0; // milliseconds
		currentUpdateTime = 0;  // milliseconds
		length = Dimensions.PULT_LENGTH;
		this.score= score;
	}
	
	/**
	 * Create a new drinker
	 */
	private void createDrinker() {
		Random ran = new Random();
		float position = ran.nextFloat() * length;

		GenericDrinker drinker = new GenericDrinker(position, currentUpdateTime, score);
		drinkersWaiting.add(drinker);
		//Gdx.app.log("BarCounter", "Created new drinker at position " + position + ". Size of drinker list: " + drinkersWaiting.size());
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
				Gdx.app.log("BarCounter", "Drinker has left. Size of drinker list: " + drinkersWaiting.size());
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

//		Gdx.app.log("BarCounter", "Update time: " + currentUpdateTime);

		updateInternal();
	}
	
	/**
	 * Internal update method.
	 */
	private void updateInternal() {

		// Remove drinkers
		updateDrinkers();

		// Add a new drinker if needed
		if (drinkersWaiting.size() < capacity)
		{
			Random ran = new Random();
			long randomTime = ran.nextLong() % meanTimeBetweenDrinkers;
			if (randomTime < 0) randomTime += meanTimeBetweenDrinkers;
			
			long deltaTime = currentUpdateTime - previousUpdateTime;
			// Make this more fancy later (Poisson process).
			if (deltaTime > randomTime) {
				createDrinker();
			}
//			Gdx.app.log("BarCounter", "Delta time " + deltaTime + " random time " + randomTime);
			}
	}
	
	/**
	 * Get list of drinkers.
	 */
	@Override
	public List<? extends Drinker> getDrinkers() {
		// TODO Auto-generated method stub
		return drinkersWaiting;
	}

	@Override
	public int giveDrink(TypeOfDrink typeOfDrink, float position,
			long timeOfReceivingDrink) {
		
		int pointsGot=-1;
		for (GenericDrinker drinker : drinkersWaiting) {
			if(drinker.isBeerInRange(position) && drinker.receiveDrink(typeOfDrink)){
				 pointsGot = ScoringValues.calculatePoints(timeOfReceivingDrink - drinker.getOrderTime()); 
			}
		} 
		return pointsGot;
	}

	@Override
	public Set<TypeOfDrink> getViableDrinks() {
		Set<TypeOfDrink> viableDrinks = new HashSet<TypeOfDrink>();
		for (Drinker drinker : drinkersWaiting) {
			for (DrinkOrder order : drinker.getDrinkOrders()) {
				viableDrinks.add(order.getWhatsTheDrink());
			}
		}
		return viableDrinks;
	}
	
	
}

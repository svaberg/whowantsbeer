package ninja.epsilon.drinkers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;


public class GenericDrinker implements Drinker {
	/**
	 * Creation time in milliseconds [ms] 
	 * Time drinker appeared in the bar.
	 */
	long creationTime;

	/**
	 * Persistence time in milliseconds [ms] 
	 * Patience - time until drinker leaves the bar.
	 */
	long persistenceTime;
	
	/** 
	 * Whether the drinker has waited too long and has left.
	 */
	boolean hasWaitedTooLong;
	
	/**
	 * Whether the drinker has received all orders (and has left).
	 */
	boolean hasReceivedAllOrders;
	
	/**
	 * Position of the drinker in the bar
	 */
	private float position;
	
	/**
	 * Radius of the drinker in the bar
	 */
	private float radius;
	
	/**
	 * Drinks being ordered by the drinker. When all drinks have been received
	 * the drinker will be satisfied and leave the bar (and a tip!).
	 */
	List<GenericDrinkOrder> drinkOrders;
	
	/**
	 * Create a new drinker with the default persistence time
	 * @param nowTime the current time in milliseconds.
	 */
	public GenericDrinker(float position, long nowTime) {
		this.setPosition(position);
		this.persistenceTime = 3000; // milliseconds
		this.creationTime = nowTime;
		this.drinkOrders = new ArrayList<GenericDrinkOrder>();
		
		// Add a drink order.
		this.drinkOrders.add(new GenericDrinkOrder(TypeOfDrink.blondBeer, 0, creationTime));
	}
	
	/**
	 * Update the state of the drinker with the current time.
	 * @param nowTime current time
	 */
	public void update(long nowTime) {

		// Check whether all orders have been received.
		hasReceivedAllOrders = true;
		for (GenericDrinkOrder drinkOrder : drinkOrders) {
			hasReceivedAllOrders &= drinkOrder.isReceived();
		}
		if (hasReceivedAllOrders) Gdx.app.log("GenericDrinker", "Drinker has received all orders and is leaving.");

		// Check whether the drinker has waited too long and thus will leave.
		long timeWaited = nowTime - creationTime;
		hasWaitedTooLong = (timeWaited > persistenceTime);
		if (hasWaitedTooLong) Gdx.app.log("GenericDrinker", "Drinker has waited too long and is leaving.");

	}
	
	/** 
	 * Returns true if the drinker has waited too long and thus will leave.
	 * @param nowTime
	 * @return true if the drinker has waited too long, otherwise false
	 */
	public boolean hasWaitedTooLong() {
		return hasWaitedTooLong;
	}
	
	/**
	 * Returns true if the drinker has received all orders. Otherwise returns false.
	 */
	public boolean hasReceivedAllOrders() {
		return hasReceivedAllOrders;
	}
	
	/**
	 * Returns true if the drinker has left the bar at this time.
	 * @param nowTime the current time in milliseconds
	 * @return whether the drinker has left the bar
	 */
	public boolean hasLeft() {
		return hasReceivedAllOrders() || hasWaitedTooLong();
	}

	@Override
	public DrinkerType GetDrinkerType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float GetX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getPosition() {
		return position;
	}

	public void setPosition(float position) {
		this.position = position;
	}

	float getRadius() {
		return radius;
	}

	void setRadius(float radius) {
		this.radius = radius;
	}	
}

package ninja.epsilon.drinkers;

import java.util.List;


public class GenericDrinker implements Drinker {
	/**
	 * Creation time in milliseconds [ms] 
	 * Time drinker appears.
	 */
	long creationTime;

	/**
	 * Persistence time in milliseconds [ms] 
	 * Patience - time until drinker leaves the bar.
	 */
	long persistenceTime;
	
	boolean hasWaitedTooLong;
	boolean hasReceivedAllOrders;
	
	/**
	 * Drinks being ordered by the drinker. When all drinks have been received
	 * the drinker will be satisfied and leave the bar (and a tip!).
	 */
	List<GenericDrinkOrder> drinkOrders;
	
	/**
	 * Create a new drinker with the default persistence time
	 * @param nowTime the current time in milliseconds.
	 */
	public GenericDrinker(long nowTime) {
		persistenceTime = 3000; // milliseconds
		creationTime = nowTime;
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

		// Check whether the drinker has waited too long and thus will leave.
		long timeWaited = nowTime - creationTime;
		hasWaitedTooLong = (timeWaited > persistenceTime);

	}
	
	/** 
	 * Returns true if the drinker has waited too long and thus will leave.
	 * @param nowTime
	 * @return true if the drinker has waited too long, otherwise false
	 */
	public boolean hasWaitedTooLong(long nowTime) {
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
	public boolean hasLeft(long nowTime) {
		return hasReceivedAllOrders() || hasWaitedTooLong(nowTime);
	}	
}

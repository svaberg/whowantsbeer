package ninja.epsilon.drinkers;

import java.util.List;


public class Drinker {
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
	
	/**
	 * Drinks being ordered by the drinker. When all drinks have been received
	 * the drinker will be satisfied and leave the bar (and a tip!).
	 */
	List<DrinkOrder> drinkOrders;
	
	/**
	 * Returns true if the drinker has received all orders. Otherwise returns false.
	 */
	boolean hasReceivedAllOrders() {
		boolean hasReceivedAllOrders = true;
		for (DrinkOrder drinkOrder : drinkOrders) {
			hasReceivedAllOrders &= drinkOrder.isReceived();
		}
		return hasReceivedAllOrders;
	}
	
	/** 
	 * Returns true if the drinker has waited too long and thus will leave.
	 * @param nowTime
	 * @return true if the drinker has waited too long, otherwise false
	 */
	boolean hasWaitedTooLong(long nowTime) {
		long timeWaited = nowTime - creationTime;
		return (timeWaited > persistenceTime);
	}
	
	
}

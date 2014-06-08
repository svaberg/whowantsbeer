package ninja.epsilon.drinkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	DrinkerType type;
	
	/**
	 * Drinks being ordered by the drinker. When all drinks have been received
	 * the drinker will be satisfied and leave the bar (and a tip!).
	 */
	private List<GenericDrinkOrder> drinkOrders;
	
	/**
	 * Create a new drinker with the default persistence time
	 * @param nowTime the current time in milliseconds.
	 */
	public GenericDrinker(float position, long nowTime) {
		this.setPosition(position);
		this.persistenceTime = 6000; // milliseconds
		this.creationTime = nowTime;
		this.drinkOrders = new ArrayList<GenericDrinkOrder>();
		
		{
			Random ran = new Random();
			int typeId = ran.nextInt(Drinker.DrinkerType.values().length);
			this.type = DrinkerType.values()[typeId];
		}
		
		// Add a few drink orders.
		{
			Random ran = new Random();
			int exp = ran.nextInt(8);
			for (int i = 1; i < 8; i *= 2)
			{
				if (exp % i == 0) {
					addDrinkOrder();
				}
			}
		}
	}
	
	/**
	 * Returns true if the glass resting position is within the grasp of the drinker.
	 * @param glassRestingPosition
	 * @return
	 */
	public boolean isBeerInRange(float glassRestingPosition) {
		float distance = Math.abs(glassRestingPosition - this.position);
		return distance < radius;
	}
	
	/**
	 * 
	 * @param typeOfDrink
	 */
	public void receiveDrink(TypeOfDrink typeOfDrink) {
		for (GenericDrinkOrder drinkOrder : drinkOrders) {
			if (drinkOrder.getWhatsTheDrink() == typeOfDrink) {
				drinkOrder.setReceived();
				Gdx.app.log("GenericDrinker", "Drinker has received an ordered" + typeOfDrink.toString());
				break;
			}
		}
	}
		
	/**
	 * Add a new drink order for a random drink
	 */
	private void addDrinkOrder() {
		Random ran = new Random();
		int typeId = ran.nextInt(TypeOfDrink.values().length);
		addDrinkOrder(TypeOfDrink.values()[typeId]);		
	}
	
	/**
	 * Add a new drink order for a specific type of drink.
	 * @param typeOfDrink
	 */
	private void addDrinkOrder(TypeOfDrink typeOfDrink) {
		this.getDrinkOrders().add(new GenericDrinkOrder(typeOfDrink, this.position, creationTime));	
		Gdx.app.log("GenericDrinker", this.type.toString() + " Drinker passed order for " + typeOfDrink.toString());
	}
	
	/**
	 * Update the state of the drinker with the current time.
	 * @param nowTime current time
	 */
	public void update(long nowTime) {

		// Check whether all orders have been received.
		hasReceivedAllOrders = true;
		for (GenericDrinkOrder drinkOrder : getDrinkOrders()) {
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
		return type;
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

	public List<GenericDrinkOrder> getDrinkOrders() {
		return drinkOrders;
	}	
}

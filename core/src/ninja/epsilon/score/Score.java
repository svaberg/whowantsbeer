package ninja.epsilon.score;

import ninja.epsilon.GameLevel;
import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;
import ninja.epsilon.orders.GenericOrders;
import ninja.epsilon.orders.Orders;


public class Score implements Scorer{

	private int currentScore;
	private int chances;
	public int getScore() {
		return currentScore;
	}
	
	private Orders orders;
	
	public Score(){
		orders = new GenericOrders();
		currentScore = 0;
		chances = ScoringValues.getNrOfChances();
	}
	
	public void gotOneDrink(TypeOfDrink typeOfDrink, float position, long timeOfReceivingDrink)	{
		//Match the beer with wating orders
		int poitnsGot = orders.matchDrink(position, typeOfDrink, timeOfReceivingDrink);
		if(poitnsGot==-1){
			//penalty
			chances = chances > 0? chances -1 : 0;
		}
		else{
			currentScore+=poitnsGot;
		}
	}
	
	//Add the order in the list of orders
	public void gotOneOrder(DrinkOrder order){
		orders.addOrder(order);
	}

	public GameLevel getGameLevel() {
		return ScoringValues.calculateLevel(currentScore); 
		}


	//Remove order from the list of orders
	public void removeOrder(DrinkOrder order) {
		orders.removeOrder(order);
	}

	//Remove all orders at position from the list of orders
	public void drinkerLeft(float position) {
		orders.removeAllOrdersAtPosition(position);
	}

	//Flag Game Over
	public boolean gameOver() {
		return chances <= 0;
	}

}

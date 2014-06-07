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
		
		ScoringValues sv= new ScoringValues();
		
		chances = sv.getNrOfChances();
	}
	
	public void gotOneDrink(TypeOfDrink typeOfDrink, int position, long timeOfReceivingDrink)	{
		//Match the beer with wating orders
		int poitnsGot = orders.matchDrink(position, typeOfDrink, timeOfReceivingDrink);
		if(poitnsGot==-1){
			//penalty
			chances -=1;
			if(chances<=0){
				//Game over
			}
		}
		else{
			currentScore+=poitnsGot;
		}
	}
	
	public void gotOneOrder(DrinkOrder order){
		//Add the order in the list of orders
		orders.addOrder(order);
	}

	public GameLevel getGameLevel() {
		if(currentScore < 100) return GameLevel.EASY;
		else if(currentScore < 500) return GameLevel.MEDIUM;
		else if(currentScore < 1000) return GameLevel.DIFFICULT;
		
		return GameLevel.HARDCORE;
	}



	public void removeOrder(DrinkOrder order) {
		orders.removeOrder(order);
	}

	
	public void drinkerLeft(int position) {
		orders.removeAllOrdersAtPosition(position);
	}

}

package ninja.epsilon.score;

import ninja.epsilon.GameLevel;
import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;
import ninja.epsilon.orders.Orders;
import ninja.epsilon.orders.GenericOrders;


public class Score implements Scorer{

	public int currentScore;
	private Orders orders;
	
	Score(){
		orders = new GenericOrders();
	}
	
	public void GotOneDrink(TypeOfDrink typeOfDrink, int position)	{
		//Match the beer with wating orders
		orders.matchDrink(position, typeOfDrink);
		//if matched increase score
		//if not send penalty
	}
	
	public void GotOneOrder(DrinkOrder order){
		//Add the order in the list of orders
		orders.addOrder(order);
	}


	public GameLevel getGameLevel() {
		// TODO Auto-generated method stub
		return GameLevel.EASY;
	}
}

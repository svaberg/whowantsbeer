package ninja.epsilon.score;

import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;
import ninja.epsilon.orders.Orders;
import ninja.epsilon.orders.PlacedOrders;


public class Score {

	public int currentScore;
	private Orders orders;
	
	Score(){
		orders = new PlacedOrders();
	}
	
	public void GotOneBeer(TypeOfDrink typeOfDrink, int position)	{
		//Match the beer with wating orders
		
		//if matched increase score
		//if not send penalty
	}
	
	public void GotOneOrder(DrinkOrder order){
		//Add the order in the list of orders
		orders.addOrder(order);
	}
}

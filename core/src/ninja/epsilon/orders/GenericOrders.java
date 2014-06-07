package ninja.epsilon.orders;

import java.util.ArrayList;

import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;

public class GenericOrders implements Orders {

	private ArrayList<DrinkOrder> waitingOrders;
	
	public GenericOrders()
	{
		waitingOrders = new ArrayList<DrinkOrder>();
	}
	
	public void addOrder(DrinkOrder order){
		waitingOrders.add(order);
	}
	
	public void removeOrder(DrinkOrder order){
		waitingOrders.remove(order);
	}


	public int matchDrink(int position, TypeOfDrink typeOfDrink) {
		int foundDrink = -1;
		for (int i = 0; i < waitingOrders.size(); i++) {
		   //System.out.println("Index: " + i + " - Item: " + waitingOrders.get(i));
			DrinkOrder order = waitingOrders.get(i);
			if(order.getPosition() == position && order.getWhatsTheDrink() == typeOfDrink){

				foundDrink=i;
				break;
			}
		 }
		
		if	(foundDrink > -1) {
			DrinkOrder order = waitingOrders.get(foundDrink);
			return 0;
		}
		else{
			//something else
			return 0;
		}
	}
}

package ninja.epsilon.orders;

import java.util.ArrayList;
import java.util.List;

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
		int foundOrder = -1;
		
		//search the order
		for (int i = 0; i < waitingOrders.size(); i++) {
			DrinkOrder order_i = waitingOrders.get(i);
			if(order.getPosition() == order_i.getPosition() && order.getWhatsTheDrink() == order_i.getWhatsTheDrink()){
				foundOrder=i;
				break;
			}
		 }
		//remove the order
		waitingOrders.remove(foundOrder);
	}

	public int matchDrink(int position, TypeOfDrink typeOfDrink, long timeOfReceivingDrink) {
		int foundDrink = -1;
		
		//search the order
		for (int i = 0; i < waitingOrders.size(); i++) {
			DrinkOrder order = waitingOrders.get(i);
			if(order.getPosition() == position && order.getWhatsTheDrink() == typeOfDrink){
				order.setReceived();
				foundDrink=i;
				break;
			}
		 }
		
		//if found, get the point and remove it from the waiting list
		if	(foundDrink>-1){
			DrinkOrder order = waitingOrders.get(foundDrink);
			int pointsGot = order.getPointsReceived(timeOfReceivingDrink);
			waitingOrders.remove(foundDrink);
			
			return pointsGot;
		}
		
		//no matching order found, lose points
		return -1;
		
	}

	
	public void removeAllOrdersAtPosition(int position) {
		List<Integer> orders2Remove = new ArrayList<Integer>(); 
		//search the all orders at the position
		for (int i = 0; i < waitingOrders.size(); i++) {
			DrinkOrder order_i = waitingOrders.get(i);
			if(position == order_i.getPosition()){
					orders2Remove.add(i);
			}
		}
		
		//remove all marked orders
		for (int i = 0; i < orders2Remove.size(); i++) {
			waitingOrders.remove(i);
		}
		
	}
}

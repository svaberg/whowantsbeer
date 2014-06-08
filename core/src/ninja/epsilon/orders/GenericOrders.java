package ninja.epsilon.orders;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.TypeOfDrink;
import ninja.epsilon.score.ScoringValues;

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
			float dist = Math.abs(order.getPosition() - order_i.getPosition());
			
			if(dist< ScoringValues.POSITION_TOLERANCE && order.getWhatsTheDrink() == order_i.getWhatsTheDrink()){
				foundOrder=i;
				break;
			}
		 }
		//remove the order
		waitingOrders.remove(foundOrder);
	}

	public int matchDrink(float position, TypeOfDrink typeOfDrink, long timeOfReceivingDrink) {
		int foundDrink = -1;
		
		Gdx.app.log("GenericOrders", "Trying to match " + position + " - " + typeOfDrink.toString());
		
		//search the order
		for (int i = 0; i < waitingOrders.size(); i++) {
			DrinkOrder order = waitingOrders.get(i);
			float dist = Math.abs(order.getPosition() - position);
	
			if(dist< ScoringValues.POSITION_TOLERANCE  && order.getWhatsTheDrink() == typeOfDrink){
				Gdx.app.log("GenericOrders", "Trying to matched succeded ");
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
			Gdx.app.log("GenericOrders", "Trying to matched failed ");
			return pointsGot;
		}
		
		//no matching order found, lose points
		return -1;
		
	}

	public void removeAllOrdersAtPosition(float position) {
		List<Integer> orders2Remove = new ArrayList<Integer>(); 
		//search the all orders at the position
		for (int i = 0; i < waitingOrders.size(); i++) {
			DrinkOrder order_i = waitingOrders.get(i);
			float dist = Math.abs(order_i.getPosition() - position);
			
			if(dist< ScoringValues.POSITION_TOLERANCE){
					orders2Remove.add(i);
			}
		}
		
		//remove all marked orders
		for (int i = 0; i < orders2Remove.size(); i++) {
			waitingOrders.remove(i);

		}
		
	}
}

package ninja.epsilon.orders;

public class PlacedOrder implements Order {

	long placedTime = 0L;
	long recivedTime = 0L;
	int position = 0;
	
	PlacedOrder(long placedTime1, int position1){
		placedTime = placedTime1;
		position = position1;
	}
	
}

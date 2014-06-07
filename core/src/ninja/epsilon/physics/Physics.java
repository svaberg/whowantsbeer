package ninja.epsilon.physics;

public interface Physics {
	/**
	 * Updates state of the world.
	 */
	void update(long t, long swipe);
	
	//TODO: Add methods to retrieve position of each glass.
}

package ninja.epsilon.physics;

public interface Physics {
	public static interface InputCallback {
		/**
		 * Callback from the input module to handle completed swipe.
		 */
		void swipe(float v);
	}

	/**
	 * Updates state of the world.
	 */
	void update(long t, long swipe);
	
	//TODO: Add methods to retrieve position of each glass.
}

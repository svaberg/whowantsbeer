package ninja.epsilon.physics;

public interface Physics {
	public static interface InputCallback {
		/**
		 * Callback from the input module to handle completed swipe.
		 */
		void swipe(float v);
	}

	public static class GlassState {
		public float x;
		public float y;
		public float angle;
	}

	/**
	 * Updates state of the world.
	 */
	void update(long t, float swipe);
	
	//TODO: Add methods to retrieve position of each glass.
	Iterable<GlassState> whereAreTheGlasses();
}

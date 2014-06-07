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

	/**
	 * Returns position and orientation of all glasses.
	 *
	 * @return iterable over glass state
	 */
	Iterable<GlassState> whereAreTheGlasses();

	/**
	 * Returns the number of glasses which fell off the counter on this iteration.
	 */
	int howManyFellOff();
}

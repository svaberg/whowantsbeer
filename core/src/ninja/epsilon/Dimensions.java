package ninja.epsilon;

public class Dimensions {

	public static float FULL_WIDTH = 4.0f; // meters
	public static float FULL_HEIGHT = 2.5f; // meters
	
	public static float VIRTUAL_WIDTH = 800f; // pixels
	public static float VIRTUAL_HEIGHT = 480f; // pixels
	public static final float ASPECT_RATIO = VIRTUAL_WIDTH/VIRTUAL_HEIGHT;
	
	// Bar is at 0.5 m height
	public static final float PULT_HEIGHT = 0.5f;
	// Bar is 3.5 m long
	public static final float PULT_LENGTH = 3.5f;
	
	public static final int BAR_COUNTER_CAPACITY = 6;
	public static final int BAR_MEAN_TIME_BETWEEN_DRINKERS = 1000;

	public static final float GLASS_HEIGHT = 0.25f;
	public static final float GLASS_WIDTH = 0.1f;
	
	public static final float DRINKER_WIDTH = 0.5f;
	public static final long DRINKER_PERSISTENCE_TIME = 6000;
	private Dimensions() {
	}
}

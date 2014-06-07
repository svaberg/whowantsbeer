package ninja.epsilon.drinkers;

public interface Drinker {
	
	public enum DrinkerType {
	    SPANISH, 
	    GERMAN, 
	    NORWEGIAN,
	    POLISH,
	    ROMANIAN 
	}
	
	/*
	 * Getter for the Drinker Type
	 */
	DrinkerType GetDrinkerType();

	/*
	 * Get X Position in pixels
	 */
	float GetX();

	/*
	 * Get Y Position in pixels
	 */
	float GetY();
	
}

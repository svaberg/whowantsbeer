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

	/**
	 * Return drinker position along bar.
	 * @return
	 */
	float getPosition();
}

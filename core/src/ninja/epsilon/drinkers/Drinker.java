package ninja.epsilon.drinkers;

import java.io.File;
import java.util.List;

public interface Drinker {
	
	public enum DrinkerType {
	    SPANISH, 
	    GERMAN, 
	    NORWEGIAN,
	    POLISH_MAN,
	    POLISH_WOMAN,
	    ROMANIAN_MAN,
	    ROMANIAN_WOMAN;
	    
	    
	    /**
	     * Base path for the drinkers' image folder
	     */
	    final static private String basePath = "drinkers";
	    
	    /**
	     * Get the path to the image file.
	     * @return
	     */
	    public String getImagePath() {
	    	String answer = basePath + File.separator;
	    	switch(this) {
	    	case SPANISH: 
	    		return answer + "spanish.png";
	    	case GERMAN: 
	    		return answer + "german.png";
	    	case NORWEGIAN: 
	    		return answer + "norwegian.png";
	    	case POLISH_MAN: 
	    		return answer + "polish_man.png";
	    	case POLISH_WOMAN: 
	    		return answer + "polish_woman.png";
	    	case ROMANIAN_MAN: 
	    		return answer + "romanian_man.png";
	    	case ROMANIAN_WOMAN: 
	    		return answer + "romanian_woman.png";
	    	default:
	    		return answer + "generic_drinker.png";	
	    	}
	    	
	    }
	    
	    
	    
	    
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
	
	/*
	 * Return Drink Orders
	 */
	List<? extends DrinkOrder> getDrinkOrders();
}

package ninja.epsilon.drinkers;

import java.io.File;

public enum TypeOfDrink {
		blondBeer (0), 
		darkBeer (1), 
		tequilaShot(2),
		//cocktail(3),
		//cactus(4)
		;
		
	
	private int type;

	private TypeOfDrink(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

	/// ... improvement.. add it in the plugin properties file/config file.
	final static private String basePath = "drinks";
	final static private String basePrefix = "spritesheet_";
	public String getPath() {
		String answer = basePath + File.separator + basePrefix; 
		switch(this)
		{
		case blondBeer:return answer + "blondBeer.atlas";
		case darkBeer:return answer + "darkBeer.atlas";
		case tequilaShot:return answer + "tequilaShot.atlas";
		//case cocktail:return answer + "cocktail.atlas";
		//case cactus:return answer + "cactus.atlas";

		};
		return answer + "beer.atlas";
	}
	
	
	/// ... improvement.. add it in the plugin properties file/config file.
	final static private String bubbleBasePath = "bubbles";
	final static private String bubbleBasePrefix = "bubble_";
	public String getBubblePath() {
		String answer = bubbleBasePath + File.separator + bubbleBasePrefix; 
		switch(this)
		{
		case blondBeer:return answer + "blondBeer.png";
		case darkBeer:return answer + "darkBeer.png";
		case tequilaShot:return answer + "tequilaShot.png";
		//case cocktail answer + "cocktail.png";
		//case cactus:return answer + "cactus.png";

		};
		return answer + "blondBeer.png";  // question mark
	}
	
	
	
}

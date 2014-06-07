package ninja.epsilon.drinkers;

import java.io.File;

public enum TypeOfDrink {
		blondBeer (0), 
		darkBeer (1), 
		tequilaShot(2),
		glass_wine(3),
		cactus(4)
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
		case glass_wine:return answer + "glass_wine.atlas";
		case cactus:return answer + "glass_cactus.atlas";

		};
		return answer + "beer.atlas";
	}
}

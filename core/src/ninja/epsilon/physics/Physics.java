package ninja.epsilon.physics;

import java.io.File;

public interface Physics {
	public static interface InputCallback {
		/**
		 * Callback from the input module to handle completed swipe.
		 */
		void swipe(float v);
	}

	public enum GlassType {
		GLASS_BEER,
		GLASS_WINE,
		GLASS_SHOT,
		GLASS_CACTUS;
		
		/// ... improvement.. add it in the plugin properties file/config file.
		final static private String basePath = "drinks";
		final static private String basePrefix = "spritesheet_";
		public String getPath() {
			String answer = basePath + File.separator + basePrefix; 
			switch(this)
			{
				case GLASS_BEER:return answer + "beer.atlas";
				case GLASS_WINE:return answer + "wine.atlas";
				case GLASS_SHOT:return answer + "shot.atlas";
				case GLASS_CACTUS:return answer + "cactus.atlas";
				
			
			};
			return answer + "beer.atlas";
		}
		
		
	};
	
	public static class GlassState {
		public float x;
		public float y;
		public float angle;
		public GlassType type;
	}

	/**
	 * Updates state of the world.
	 */
	void update(long t, float swipe);
	
	//TODO: Add methods to retrieve position of each glass.
	Iterable<GlassState> whereAreTheGlasses();
}

package ninja.epsilon.score;

import ninja.epsilon.GameLevel;

public class ScoringValues {

	private static int THRESHOLD = 6000; //6s -> kind of the same as Dimensions.DRINKER_PERSISTENCE_TIME
	public static float POSITION_TOLERANCE = 0.9F;
	
	//game over after so many missed
	public static int CHANCES_AT_GAME_START = 10;
	
	public static int calculatePoints(long timeWaiting) {
		//everthing less then the threshold gives points
		int points = Math.round((THRESHOLD - timeWaiting) / 100);
		
		//everthing more only one point
		points = Math.min(points, 1);
		return points;
	}

	public static GameLevel calculateLevel(int currentScore) {
		if(currentScore < 2 * THRESHOLD) return GameLevel.EASY;
		else if(currentScore < 4 * THRESHOLD) return GameLevel.MEDIUM;
		else if(currentScore < 6 * THRESHOLD) return GameLevel.DIFFICULT;
		
		return GameLevel.HARDCORE;
	}
	
	


}

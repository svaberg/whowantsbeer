package ninja.epsilon.score;

import ninja.epsilon.GameLevel;

public class ScoringValues {

	private static int threshold =7000; //7s
	
	
	public static int getNrOfChances() {return 10;}
	
	public static int calculatePoints(long timeWaiting) {
		//everthing less then the threshold gives points
		int points = Math.round((threshold - timeWaiting) / 100);
		
		//everthing more only one point
		points = Math.min(points, 1);
		return points;
	}

	public static GameLevel calculateLevel(int currentScore) {
		if(currentScore < 2 * threshold) return GameLevel.EASY;
		else if(currentScore < 4 * threshold) return GameLevel.MEDIUM;
		else if(currentScore < 6 * threshold) return GameLevel.DIFFICULT;
		
		return GameLevel.HARDCORE;
	}


}

package ninja.epsilon.score;

import ninja.epsilon.GameLevel;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.drinkers.TypeOfDrink;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


public class Score implements Scorer {
	private static final String TAG = Score.class.getSimpleName();

	Sound dropSound;
	private int currentScore;
	private int chances;
	public int getScore() {
		return currentScore;
	}
	
	private Drinkers drinkers;

	
	public Score(){
		currentScore = 0;
		chances = ScoringValues.CHANCES_AT_GAME_START;
		
		dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin-drop.mp3"));
	}
	
	@Override
	public boolean gotOneDrink(TypeOfDrink typeOfDrink, float position, long timeOfReceivingDrink)	{
		int poitnsGot = drinkers.giveDrink( typeOfDrink,  position,  timeOfReceivingDrink);
		
		if(poitnsGot==-1){			
			return false;
		}
		else{
			currentScore+=poitnsGot;
			dropSound.play();
			return true;
		}
	}
	
	@Override
	public void fellOneDrink() {
			//penalty
			chances = chances > 0 ? chances -1 : 0;
	}

	@Override
	public GameLevel getGameLevel() {
		return ScoringValues.calculateLevel(currentScore); 
		}


	//Flag Game Over
	@Override
	public boolean gameOver() {
		return chances == 0;
	}

	@Override
	public int getChances() {
		return chances;
	}

	@Override
	public int getNrOfFails() {
		return ScoringValues.CHANCES_AT_GAME_START - chances;
	}

	@Override
	public void setDrinkers(Drinkers drinkers) {
		this.drinkers=drinkers;
		
	}

}

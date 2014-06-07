package ninja.epsilon.score;

import ninja.epsilon.GameLevel;

public interface Scorer {
	/**
	 * Returns the current level of the game.
	 */
	GameLevel getGameLevel();
}

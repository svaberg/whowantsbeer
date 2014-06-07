package ninja.epsilon;

public enum GameLevel {
	EASY(0),
	MEDIUM(1),
	DIFFICULT(2),
	HARDCORE(3);

	private int difficulty;

	private GameLevel(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getDifficulty() {
		return difficulty;
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}

package ninja.epsilon.drinkers;

public enum TypeOfDrink {
	blondBeer(0), darkBeer(1), tequilaShot(2);
	
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
}

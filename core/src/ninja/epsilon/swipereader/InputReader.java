package ninja.epsilon.swipereader;

public interface InputReader {
	/**
	 * Reads user input and returns a number representing the strength or velocity of the swipe.
	 */
	long input(long curTime);
}

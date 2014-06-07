package ninja.epsilon.renderers;

public interface Renderer {
	/**
	 * Create. Called in the beginning to allow you to create textures, boxes and what not.
	 */
	void create();

	/**
	 * Draw and play sounds.
	 */
	void render();
}

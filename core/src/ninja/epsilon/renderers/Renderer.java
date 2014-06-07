package ninja.epsilon.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderer {
	/**
	 * Create. Called in the beginning to allow you to create textures, boxes and what not.
	 */
	void create();

	/**
	 * Draw and play sounds.
	 */
	void render(SpriteBatch spriteBatch);

	/*
	 * Dispose Renderer
	 */
	void dispose();
}

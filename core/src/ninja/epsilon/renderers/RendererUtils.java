package ninja.epsilon.renderers;

import com.badlogic.gdx.Gdx;

public class RendererUtils {
	
	public static float PixelsPerMeterX() {
		return Gdx.graphics.getWidth() / 4;
	}
	
	public static float PixelsPerMeterY() {
		return (float) (Gdx.graphics.getHeight() / 2.5);
	}

	private RendererUtils() {
	}
}

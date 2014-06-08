package ninja.epsilon.renderers;

import ninja.epsilon.Dimensions;

import com.badlogic.gdx.Gdx;

public class RendererUtils {
	
	public static float PixelsPerMeterX() {
		return Gdx.graphics.getWidth() / Dimensions.FULL_WIDTH;
	}
	
	public static float PixelsPerMeterY() {
		return Gdx.graphics.getHeight() / Dimensions.FULL_HEIGHT;
	}
	
	public static float ScaleX() {
		return Gdx.graphics.getWidth() / Dimensions.VIRTUAL_WIDTH;
	}
	
	public static float ScaleY() {
		return Gdx.graphics.getHeight() / Dimensions.VIRTUAL_HEIGHT;
	}

	private RendererUtils() {
	}
}

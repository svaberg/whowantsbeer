package ninja.epsilon.renderers;

import com.badlogic.gdx.Gdx;

public class RendererUtils {
	
	static public float PixelsPerMeterX()
	{
		return Gdx.graphics.getWidth() / 5;
	}
	
	static public float PixelsPerMeterY()
	{
		return Gdx.graphics.getHeight() / 3;
	}

}

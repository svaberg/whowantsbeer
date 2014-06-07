package ninja.epsilon.renderers;

import com.badlogic.gdx.Gdx;

public class RendererUtils {
	
	// Bar is at 0.5 m height
	static final public float PultHeight = (float) 0.5;
	
	static public float PixelsPerMeterX()
	{
		return Gdx.graphics.getWidth() / 4;
	}
	
	static public float PixelsPerMeterY()
	{
		return (float) (Gdx.graphics.getHeight() / 2.5);
	}

}

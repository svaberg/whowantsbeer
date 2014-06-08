package ninja.epsilon.swipereader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;

import ninja.epsilon.physics.Physics;
import ninja.epsilon.renderers.RendererUtils;

public class SwipeReader extends GestureDetector implements InputReader {

	private static final String TAG = "SwipeReader";
	private static float velocityX = 0;
	private static Physics.InputCallback physicsInputCallback;
	
	public SwipeReader(Physics.InputCallback callback) {
		super(new DirectionGestureListener());
		physicsInputCallback = callback;
		Gdx.input.setInputProcessor(this);
	}

	private static class DirectionGestureListener extends GestureAdapter {
		
		@Override
		public boolean fling(float x, float y, int button) {
			if (Math.abs(x) > Math.abs(y)) {
				Gdx.app.log(TAG, "Swipe velocity: " + Float.toString(velocityX));
				Gdx.app.log(TAG, "Swipe velocity adjusted: " + 2 * x / RendererUtils.PixelsPerMeterX());
				velocityX = 2 * x / RendererUtils.PixelsPerMeterX();
				
				physicsInputCallback.swipe(velocityX);
			} else {
				// Ignore the input, because we don't care about up/down swipes.
			}
			return true; 
		}
	}

	@Override
	public float input(long curTime) {
		return velocityX;
	}
}	

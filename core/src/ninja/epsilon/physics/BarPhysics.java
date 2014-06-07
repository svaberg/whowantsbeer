package ninja.epsilon.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Main physics engine class.
 *
 * The coordinate system has x axis extend from the player to the right
 * and y axis extend upwards. The origin is the player's location and the
 * initial position of each glass of beer.
 */
public class BarPhysics implements Physics {
	private static final Vector2 GRAVITY = new Vector2(0, -10.0f);
	private World world;

	public BarPhysics() {
		world = new World(GRAVITY, false); //TODO: try doSleep=true
	}

	@Override
	public void update(long t, long swipe) {
	}

}

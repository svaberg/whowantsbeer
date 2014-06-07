package ninja.epsilon.physics;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Main physics engine class.
 *
 * The coordinate system has x axis extend from the player to the right
 * and y axis extend upwards. The origin is the player's location and the
 * initial position of each glass of beer.
 */
public class BarPhysics implements Physics, Physics.InputCallback {
	private static final String TAG = BarPhysics.class.getSimpleName();

	private static final int VELOCITY_ITERATIONS = 6;
	private static final int POSITION_ITERATIONS = 2;
	private static final int MAX_GLASSES_COUNT = 10;

	private static final Vector2 GRAVITY = new Vector2(0, -10.0f);
	private static final float GLASS_MASS = 0.5f;

	private World world;
	private List<Body> glasses;
	private float prev_t;
	private boolean running;

	public static class PhysicsException extends RuntimeException {
		PhysicsException(String msg) {
			super(msg);
		}
	}

	public BarPhysics() {
		world = new World(GRAVITY, false); //TODO: try doSleep=true
		createCounter();
		glasses = new LinkedList<Body>();
		running = false;
	}

	@Override
	public void update(long t, long swipe) {
		float cur_t = (float) t;
		if (running) {  // skip first loop to make sure prev_t is valid
			doUpdate(cur_t);
		}
		prev_t = cur_t;
		running = true;
	}

	private void doUpdate(float cur_t) {
		float step_t = cur_t - prev_t;
		if (step_t <= 0.0) {
			throw new PhysicsException("Negative time step: " + step_t);
		}

		world.step(step_t, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}

	@Override
	public void swipe(float v) {
		Body glass = createGlass(v);
		glass.applyLinearImpulse(getImpulse(v), glass.getWorldCenter(), true);
		if (glasses.size() > MAX_GLASSES_COUNT) {
			glasses.remove(0);
			Gdx.app.log(TAG, "Too many glasses created, dropping...");
		}
		glasses.add(glass);
	}

	private Vector2 getImpulse(float v) {
		return new Vector2(v, 0.0f);
	}

	private Body createCounter() {
		BodyDef counterDef = new BodyDef();
		counterDef.position.set(new Vector2());
		Body counter = world.createBody(counterDef);
		PolygonShape counterShape = new PolygonShape();
		counterShape.setAsBox(3.0f, 3.0f);
		counter.createFixture(counterShape, 0.0f);
		return counter;
	}

	private Body createGlass(float v) {
		BodyDef glassDef = new BodyDef();
		glassDef.position.set(new Vector2());
		glassDef.type = BodyType.DynamicBody;
		Body glass = world.createBody(glassDef);
		PolygonShape glassShape = new PolygonShape();
		glassShape.setAsBox(0.1f, 0.2f);
		glass.createFixture(glassShape, 0.0f);
		return glass;
	}

}

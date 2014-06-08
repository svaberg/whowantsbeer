package ninja.epsilon.physics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import ninja.epsilon.drinkers.TypeOfDrink;
import ninja.epsilon.score.Scorer;

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
	private static final float MIN_STOP_VELOCITY = 0.01f;
	private static final float FALL_DETECT_THRESHOLD = -10.0f;

	private World world;
	private LinkedList<Body> glasses;
	private long prev_t;
	private boolean running;
	private Scorer scorer;
	private int fellGlassCount;

	public static class PhysicsException extends RuntimeException {
		PhysicsException(String msg) {
			super(msg);
		}
	}

	public BarPhysics(Scorer scorer) {
		world = new World(GRAVITY, false); //TODO: try doSleep=true
		createCounter();
		glasses = new LinkedList<Body>();
		running = false;
		this.scorer = scorer;
		Gdx.app.log(TAG, "NEW WORLD CREATED!");
	}

	private static GlassState bodyToState(Body body) {
		GlassState state = new GlassState();
		state.x = body.getPosition().x;
		state.y = body.getPosition().y;
		state.angle = body.getAngle();
		return state;
	}

	private class GlassIterator implements Iterator<GlassState> {
		private Iterator<Body> gi;

		public GlassIterator(Iterator<Body> gi) {
			this.gi = gi;
		}

		@Override
		public boolean hasNext() {
			return gi.hasNext();
		}

		@Override
		public GlassState next() {
			return bodyToState(gi.next());
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Hands off that glass!");
		}
	}

	@Override
	public Iterable<GlassState> whereAreTheGlasses() {
		return new Iterable<GlassState>() {
			@Override
			public Iterator<GlassState> iterator() {
				return new GlassIterator(glasses.iterator());
			}
		};
	}

	@Override
	public int howManyFellOff() {
		return fellGlassCount;
	}

	@Override
	public void update(long t, float swipe) {
		long cur_t = t;
		if (running) {  // skip first loop to make sure prev_t is valid
			doUpdate(cur_t);
		}
		prev_t = cur_t;
		running = true;
	}

	private void logGlass(Body glass) {
		Vector2 p = glass.getPosition();
		Vector2 v = glass.getLinearVelocity();
		Gdx.app.log(TAG, "x=" + p.x + "  y=" + p.y + "  vx=" + v.x + "  vy=" + v.y);
	}

	private void logGlasses() {
		for (Body glass : glasses) {
			logGlass(glass);
		}
	}

	private void doUpdate(long cur_t) {
		float step_t = (cur_t - prev_t) / 1000.0f;
		//Gdx.app.log(TAG, "dt = " + step_t);
		if (step_t <= 0.0) {
			//throw new PhysicsException("Non-positive time step: " + step_t);
			// Skip iteration since another one just happened.
			// This happens for example when one resizes the window
			// or otherwise triggers a refresh.
			return;
		}

		fellGlassCount = 0;
		world.step(step_t, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		logGlasses();

		for (ListIterator<Body> i = glasses.listIterator(); i.hasNext();) {
			Body glass = i.next();
			if (glass.getLinearVelocity().isZero(MIN_STOP_VELOCITY)) {
				Gdx.app.log(TAG, "Glass stopped!");
				scorer.gotOneDrink(TypeOfDrink.blondBeer, glass.getPosition().x, cur_t);
				world.destroyBody(glass);
				i.remove();
			}
			if (glass.getPosition().y < FALL_DETECT_THRESHOLD) {
				Gdx.app.log(TAG, "Glass has fallen off the counter!");
				fellGlassCount++;
				world.destroyBody(glass);
				i.remove();
			}
		}
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
		return new Vector2(v / 1000.0f, 0.0f);
	}

	private Body createCounter() {
		BodyDef counterDef = new BodyDef();
		counterDef.position.set(new Vector2(0.0f, 0.0f));
		Body counter = world.createBody(counterDef);
		PolygonShape counterShape = new PolygonShape();
		counterShape.setAsBox(3.0f, 1.0f);
		counter.createFixture(counterShape, 0.0f);
		return counter;
	}

	private Body createGlass(float v) {
		Gdx.app.log(TAG, "Created glass!");
		BodyDef glassDef = new BodyDef();
		glassDef.position.set(new Vector2(0.0f, 2.0f));
		glassDef.type = BodyType.DynamicBody;
		Body glass = world.createBody(glassDef);
		PolygonShape glassShape = new PolygonShape();
		glassShape.setAsBox(1.0f, 1.0f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = glassShape;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.1f;
		glass.createFixture(fixtureDef);
		return glass;
	}
}

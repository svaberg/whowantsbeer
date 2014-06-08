package ninja.epsilon.physics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import ninja.epsilon.Dimensions;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.drinkers.TypeOfDrink;
import ninja.epsilon.score.Scorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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
	private static final float MIN_STOP_VELOCITY = 0.01f;
	private static final float FALL_DETECT_THRESHOLD = -10.0f;

	private World world;
	private LinkedList<Glass> glasses;
	private long prev_t;
	private boolean running;
	private Scorer scorer;
	private int fellGlassCount;
	private Drinkers drinkers;

	private static class Glass {
		private Body body;
		private TypeOfDrink type;

		public Glass(Body body, TypeOfDrink type) {
			this.body = body;
			this.type = type;
		}

		public Body getBody() {
			return body;
		}

		public TypeOfDrink getType() {
			return type;
		}
	}

	public static class PhysicsException extends RuntimeException {
		PhysicsException(String msg) {
			super(msg);
		}
	}

	public BarPhysics(Scorer scorer, Drinkers drinkers) {
		world = new World(GRAVITY, false); //TODO: try doSleep=true
		createCounterBody();
		glasses = new LinkedList<Glass>();
		running = false;
		this.scorer = scorer;
		this.drinkers = drinkers;
		Gdx.app.log(TAG, "NEW WORLD CREATED!");
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	private GlassState bodyToState(Body body) {
		GlassState state = new GlassState();
		state.x = body.getPosition().x;
		state.y = body.getPosition().y;
		state.angle = body.getAngle();
		state.type = drinkers.drinkTypesOrderedNotReceived().iterator().next();
		return state;
	}

	private class GlassIterator implements Iterator<GlassState> {
		private Iterator<Glass> gi;

		public GlassIterator(Iterator<Glass> gi) {
			this.gi = gi;
		}

		@Override
		public boolean hasNext() {
			return gi.hasNext();
		}

		@Override
		public GlassState next() {
			return bodyToState(gi.next().getBody());
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

	private void logGlass(Glass glass) {
		Vector2 p = glass.getBody().getPosition();
		Vector2 v = glass.getBody().getLinearVelocity();
		//Gdx.app.log(TAG, "x=" + p.x + "  y=" + p.y + "  vx=" + v.x + "  vy=" + v.y);
	}

	private void logGlasses() {
		for (Glass glass : glasses) {
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

		for (ListIterator<Glass> i = glasses.listIterator(); i.hasNext();) {
			Glass glass = i.next();
			Body body = glass.getBody();
			if (body.getLinearVelocity().isZero(MIN_STOP_VELOCITY)) {
				float x = glass.getBody().getPosition().x;
				if (scorer.gotOneDrink(TypeOfDrink.blondBeer, x, cur_t)) {
					Gdx.app.log(TAG, "Glass claimed at x=" + x);
					world.destroyBody(body);
					i.remove();
				} else {
					Gdx.app.log(TAG,  "Glass stopped unclaimed at x=" + x);
				}
			}
		}
		for (ListIterator<Glass> i = glasses.listIterator(); i.hasNext();) {
			Glass glass = i.next();
			Body body = glass.getBody();
			if (body.getPosition().y < FALL_DETECT_THRESHOLD) {
				Gdx.app.log(TAG, "Glass has fallen off the counter!");
				scorer.fellOneDrink();
				fellGlassCount++;
				world.destroyBody(body);
				i.remove();
			}
		}
	}

	@Override
	public void swipe(float v) {
		Body body = createGlassBody(v);
		body.applyLinearImpulse(getImpulse(v), body.getWorldCenter(), true);
		if (glasses.size() > MAX_GLASSES_COUNT) {
			world.destroyBody(glasses.remove(0).getBody());
			Gdx.app.log(TAG, "Too many glasses created, dropping...");
		}
		glasses.add(new Glass(body, getNextType()));
	}

	private Vector2 getImpulse(float v) {
		float impulse = 0.033f * v / 20.0f;
		Gdx.app.log(TAG, "Applying impulse " + impulse);
		return new Vector2(impulse, 0.0f);
	}

	private TypeOfDrink getNextType() {
		return TypeOfDrink.blondBeer;
	}

	private Body createCounterBody() {
		BodyDef counterDef = new BodyDef();
		counterDef.position.set(new Vector2(0.0f, 0.0f));
		Body counter = world.createBody(counterDef);
		PolygonShape counterShape = new PolygonShape();
		counterShape.setAsBox(Dimensions.PULT_LENGTH, Dimensions.PULT_HEIGHT);
		counter.createFixture(counterShape, 0.0f);
		return counter;
	}

	private Body createGlassBody(float v) {
		Gdx.app.log(TAG, "Created glass!");
		BodyDef glassDef = new BodyDef();
		glassDef.position.set(new Vector2(0.0f, Dimensions.PULT_HEIGHT + Dimensions.GLASS_HEIGHT/2.0f));
		glassDef.type = BodyType.DynamicBody;
		Body glass = world.createBody(glassDef);
		PolygonShape glassShape = new PolygonShape();
		glassShape.setAsBox(Dimensions.GLASS_WIDTH/2.0f, Dimensions.GLASS_HEIGHT/2.0f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = glassShape;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.01f;
		glass.createFixture(fixtureDef);
		return glass;
	}
}

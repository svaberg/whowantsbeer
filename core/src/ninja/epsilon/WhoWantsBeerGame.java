
package ninja.epsilon;

import java.util.ArrayList;
import java.util.List;

import ninja.epsilon.drinkers.DrinkerPool;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.physics.BarPhysics;
import ninja.epsilon.physics.Physics;
import ninja.epsilon.renderers.Renderer;
import ninja.epsilon.score.Scorer;
import ninja.epsilon.swipereader.InputReader;
import ninja.epsilon.swipereader.SwipeReader;

import com.badlogic.gdx.ApplicationAdapter;

public class WhoWantsBeerGame extends ApplicationAdapter {
	private Drinkers drinkers;
	private Physics physics;
	private List<Renderer> renderers;
	private Scorer scorer;
	private InputReader inputReader;
	
	@Override
	public void create () {
		renderers = new ArrayList<Renderer>();
		physics = new BarPhysics();
		drinkers = new DrinkerPool();
		inputReader = new SwipeReader();
//		scorer = new Scorer();

		for (Renderer renderer : renderers) {
			renderer.create();
		}
	}

	@Override
	public void render () {
		long t = System.currentTimeMillis();
//		long swipe = inputReader.input(t);
//		physics.update(t, swipe);
		drinkers.update(t, GameLevel.EASY);
//		for (Renderer renderer : renderers) {
//			renderer.render();
//		}
	}
}



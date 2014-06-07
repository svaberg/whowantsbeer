
package ninja.epsilon;

import com.badlogic.gdx.ApplicationAdapter;

import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.physics.Physics;
import ninja.epsilon.renderers.DrinkRenderer;
import ninja.epsilon.renderers.DrinkersRenderer;
import ninja.epsilon.renderers.Renderer;
import ninja.epsilon.score.Scorer;
import ninja.epsilon.swipereader.InputReader;

import java.util.ArrayList;
import java.util.List;

public class WhoWantsBeerGame extends ApplicationAdapter {
	private Drinkers drinkers;
	private Physics physics;
	private List<Renderer> renderers = new ArrayList<Renderer>();
	private Scorer scorer;
	private InputReader inputReader;
	
	@Override
	public void create () {
		renderers.add(new DrinkersRenderer());
		renderers.add(new DrinkRenderer());
		
		
		
		for (Renderer renderer : renderers) {
			renderer.create();
		}
	}

	@Override
	public void render () {
		long t = System.currentTimeMillis();
		long swipe = inputReader.input(t);
		physics.update(t, swipe);
		drinkers.update(t, scorer.getGameLevel());
		for (Renderer renderer : renderers) {
			renderer.render();
		}
	}
}



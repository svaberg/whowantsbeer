
package ninja.epsilon;

import java.util.ArrayList;
import java.util.List;

import ninja.epsilon.drinkers.BarCounter;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.physics.BarPhysics;
import ninja.epsilon.physics.Physics;
import ninja.epsilon.physics.Physics.InputCallback;
import ninja.epsilon.renderers.BackgroundRenderer;
import ninja.epsilon.renderers.DashboardRenderer;
import ninja.epsilon.renderers.DrinkRenderer;
import ninja.epsilon.renderers.DrinkersRenderer;
import ninja.epsilon.renderers.Renderer;
import ninja.epsilon.score.Score;
import ninja.epsilon.score.Scorer;
import ninja.epsilon.swipereader.InputReader;
import ninja.epsilon.swipereader.SwipeReader;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class WhoWantsBeerGame extends ApplicationAdapter {
	private static final String TAG = WhoWantsBeerGame.class.getSimpleName();

	private Drinkers drinkers;
	private BarPhysics physics;
	private List<Renderer> renderers;
	private Scorer scorer;
	private InputReader inputReader;
	
	private Renderer drinkersRenderer;
	private Renderer dashboardRenderer;
	private Renderer backgroundRenderer;
	private Renderer drinkRenderer = null;
	
	private SpriteBatch spriteBatch = null;
	
	@Override
	public void create () {
		renderers = new ArrayList<Renderer>();
		scorer = new Score();
		physics = new BarPhysics(scorer);
		drinkers = new BarCounter();
		inputReader = new SwipeReader(physics);

		renderers.add(backgroundRenderer = new BackgroundRenderer());
		renderers.add(drinkRenderer = new DrinkRenderer(physics));
		renderers.add(drinkersRenderer = new DrinkersRenderer(drinkers));
		renderers.add(dashboardRenderer = new DashboardRenderer(scorer));

		spriteBatch = new SpriteBatch();
		for (Renderer renderer : renderers) {
			renderer.create();
			
			if(scorer.gameOver())
			{
			
			}
		}
	}

	@Override
	public void render() {
		long t = System.currentTimeMillis();
		physics.update(t, inputReader.input(t));
		drinkers.update(t, GameLevel.EASY);
		
		spriteBatch.begin();
		for (Renderer renderer : renderers) {
			renderer.render(spriteBatch);
		}
		spriteBatch.end();
	}
	
	@Override
	public void dispose() {
		for (Renderer renderer : renderers) {
			renderer.dispose();
		}
	}
}



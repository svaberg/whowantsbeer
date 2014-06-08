package ninja.epsilon;

import java.util.ArrayList;
import java.util.List;

import ninja.epsilon.drinkers.BarCounter;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.physics.BarPhysics;
import ninja.epsilon.renderers.BackgroundRenderer;
import ninja.epsilon.renderers.BarRenderer;
import ninja.epsilon.renderers.DashboardRenderer;
import ninja.epsilon.renderers.DrinkRenderer;
import ninja.epsilon.renderers.DrinkersRenderer;
import ninja.epsilon.renderers.Renderer;
import ninja.epsilon.score.Score;
import ninja.epsilon.score.Scorer;
import ninja.epsilon.swipereader.InputReader;
import ninja.epsilon.swipereader.SwipeReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	
	final WhoWantsBeerGame game;

	private static final String TAG = WhoWantsBeerGame.class.getSimpleName();

	private Drinkers drinkers;
	private BarPhysics physics;
	private List<Renderer> renderers;
	private Scorer scorer;
	private InputReader inputReader;
	
	private Renderer drinkersRenderer;
	private Renderer dashboardRenderer;
	private Renderer backgroundRenderer;
	private Renderer barRenderer;
	private Renderer drinkRenderer = null;
	
	private SpriteBatch spriteBatch = null;
	long start = 0; // Start of game
	
	Screen gameOverScreen;
	
	GameScreen(final WhoWantsBeerGame game) {
		this.game = game;
		gameOverScreen = new GameOverScreen(game);
		create ();
	}
	
	public void create () {
		start = System.currentTimeMillis();

		renderers = new ArrayList<Renderer>();
		scorer = new Score();
		physics = new BarPhysics(scorer);
		drinkers = new BarCounter(scorer);
		scorer.setDrinkers(drinkers);
		inputReader = new SwipeReader(physics);
		
		renderers.add(backgroundRenderer = new BackgroundRenderer());
		renderers.add(drinkersRenderer = new DrinkersRenderer(drinkers));
		renderers.add(barRenderer = new BarRenderer());
		renderers.add(drinkRenderer = new DrinkRenderer(physics));
		renderers.add(dashboardRenderer = new DashboardRenderer(scorer));

		spriteBatch = new SpriteBatch();
		for (Renderer renderer : renderers) {
			renderer.create();
		}
	}
	
	@Override
	public void render(float delta) {
		long cur_t = System.currentTimeMillis();
		
		physics.update(cur_t, inputReader.input(cur_t));
		drinkers.update(cur_t, GameLevel.EASY);
		
		spriteBatch.begin();
		
		
		for (Renderer renderer : renderers) {
			renderer.render(spriteBatch);
		}
		spriteBatch.end();

		
		Long javaHeap = Gdx.app.getJavaHeap();
		Long nativeHeap = Gdx.app.getNativeHeap();
		
		Gdx.app.log(TAG, "S: " + ((System.currentTimeMillis() - start) / 1000) + " JH: " + Float.toString(javaHeap.floatValue()/1024f/1024f) + " NH: " + Float.toString(nativeHeap.floatValue()/1024f/1024f));
	
		if (scorer.gameOver()) {
			
			 Gdx.app.log(TAG, "Game over");
			
			 renderers.clear();
			 hide();
			  game.setScreen(gameOverScreen);
			 
			 // this.dispose();
		}
	}

	public void dispose() {
		for (Renderer renderer : renderers) {
			renderer.dispose();
		}
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


}

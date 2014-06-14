package ninja.epsilon;

//import java.awt.Button;

import ninja.epsilon.renderers.Renderer;
import ninja.epsilon.renderers.RendererUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameOverScreen implements Screen {

	final WhoWantsBeerGame game;

	int score = 0;
//	String scoreTxt = "";
//	Stage stage;
//	SpriteBatch batch;
//	
	private BitmapFont Font = null;
	private SpriteBatch spriteBatch = null;
	
	GameOverScreen(final WhoWantsBeerGame game, int score){
		this.game = game;
		this.score = score;
		
		spriteBatch = new SpriteBatch();
		
		Font = new BitmapFont();
		Font.setColor(Color.ORANGE);

	}
	
	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0f, .0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		batch.begin();
//	    font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
//	    font.draw(batch, scoreTxt, 25, 100);
//	    batch.end();
		
		spriteBatch.begin();
		Font.draw(spriteBatch, "Your awesome score is: " + score, 50, 100);
		Font.draw(spriteBatch, "This game is over!" , 50, 80);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		System.out.print("Game over screen");
		
//		batch = new SpriteBatch();
//	    font = new BitmapFont();
//	    stage = new Stage();
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

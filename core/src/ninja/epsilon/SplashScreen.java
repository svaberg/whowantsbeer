/**
 * 
 */
package ninja.epsilon;

import java.io.File;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Certya
 *
 */
public class SplashScreen implements Screen {

	private SpriteBatch spriteBatch;
    private Texture splsh;
    
	private WhoWantsBeerGame refGame = null;
	public SplashScreen(WhoWantsBeerGame game) {
		this.refGame = game;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         spriteBatch.begin();
         spriteBatch.draw(splsh, 0, 0);
         spriteBatch.end();
         
         if(Gdx.input.justTouched())
        	 refGame.setScreen(new GameScreen(refGame));

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		 spriteBatch = new SpriteBatch();
         splsh = new Texture(Gdx.files.internal("screens" + File.separator + "whowantsbeer.png"));

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
	
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
	

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {


	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		splsh.dispose();

	}

}

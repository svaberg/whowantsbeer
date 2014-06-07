package ninja.epsilon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WhoWantsBeerGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	// this is a comment 2  
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// we need a ninja!
		// aa
		img = new Texture("ninja.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 50, 50);
		batch.end();
	}
}

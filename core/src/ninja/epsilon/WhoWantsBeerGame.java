
package ninja.epsilon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WhoWantsBeerGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture imgBadLogic;
	boolean isWhite;
	// this is a comment 2 3 4 5 6
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// we need a ninja!
		// aa
		img = new Texture("ninja.png");
		imgBadLogic = new Texture("badlogic.jpg");
		isWhite=false;
	}

	@Override
	public void render () {
		if (isWhite) {
			Gdx.gl.glClearColor(1, 1, 1, 1);
		} else {
			Gdx.gl.glClearColor(1, 1, 0, 1);			
		}
		isWhite = !isWhite;
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 50, 50);
		batch.draw(img, 250, 50);
		batch.draw(img, 150, 150);
		batch.end();
	}
}



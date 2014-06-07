
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
	int color;
	// this is a comment 2 3 4 5 6
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// we need a ninja!
		// aa
		img = new Texture("ninja.png");
		imgBadLogic = new Texture("badlogic.jpg");
		color=0;
	}

	@Override
	public void render () {
		float red   = (float)(((color >> 0)  % 256 + 0) / 255.0);
		float green = (float)(((color >> 4)  % 256 + 0) / 255.0);
		float blue  = (float)(((color >> 16) % 256 + 0) / 255.0);
		Gdx.gl.glClearColor(red, green, blue, 1);			
		
		color++;

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 50, 50);
		batch.draw(img, 250, 50);
		batch.draw(img, 150, 150);
		batch.end();
	}
}



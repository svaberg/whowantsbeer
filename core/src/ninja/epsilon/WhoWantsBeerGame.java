<<<<<<< HEAD

package ninja.epsilon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class WhoWantsBeerGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	// this is a comment 2 3 4 5 6
	
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

=======
package ninja.epsilon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WhoWantsBeerGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	// this is a comment 2 3 4 5 6
	
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
		batch.draw(img, 450, 450);
		batch.end();
	}
}
>>>>>>> cad62e5f17ad9c6fb016a54aa4f31d3a4ab7e2b4


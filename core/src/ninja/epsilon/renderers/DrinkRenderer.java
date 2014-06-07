/**
 * 
 */
package ninja.epsilon.renderers;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class DrinkRenderer implements Renderer {
	
	private SpriteBatch spriteBatch = null;
	private Sprite sprite = null;
	private Texture texture = null;
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch(); 
		texture  = new Texture(Gdx.files.internal("drinks" + File.separator + "beer_01.jpg"));
		sprite = new Sprite(texture);
	}
	

	/* (non-Javadoc)
	 * @see ninja.epsilon.renderers.Renderer#render()
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		spriteBatch.begin();
		sprite.setPosition(0,0);
        sprite.draw(spriteBatch);
        spriteBatch.end();
	}
	
    @Override
    public void dispose() {
    	spriteBatch.dispose();

		if(texture != null)
			texture.dispose();
	//	super.dispose();
    }

}

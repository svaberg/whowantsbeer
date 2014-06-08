package ninja.epsilon.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BarRenderer implements Renderer {
	
	private Texture barImg;
	
	@Override
	public void create() {
	    barImg = new Texture(Gdx.files.internal("bar.png"));
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		
		spriteBatch.draw(barImg, 0, 0);

	}

	@Override
	public void dispose() {
		barImg.dispose();
	}

}

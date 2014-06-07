package ninja.epsilon.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class BackgroundRenderer implements Renderer {
	
	@Override
	public void create() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

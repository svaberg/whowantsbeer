package ninja.epsilon.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class BackgroundRenderer implements Renderer {
	
	private Music music;
	
	@Override
	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("barmusic.wav"));
		//music.setVolume(0.5f); 
		music.setLooping(true);
	    music.play();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose() {
		music.dispose();
	}

}

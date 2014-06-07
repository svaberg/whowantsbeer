package ninja.epsilon.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundRenderer implements Renderer {
	
	private Music music;
	private Texture backgroundImg;
	
	@Override
	public void create() {
		
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/barmusic.mp3"));
		music.setVolume(0.5f); 
		music.setLooping(true);
	    music.play();
	    
	    backgroundImg = new Texture(Gdx.files.internal("background-paper.jpg"));
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		Gdx.gl.glClearColor(.5f, .5f,.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.draw(backgroundImg, 0, 0);

	}

	@Override
	public void dispose() {
		music.dispose();
	}

}

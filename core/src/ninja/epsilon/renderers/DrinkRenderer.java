/**
 * 
 */
package ninja.epsilon.renderers;

import java.io.File;

import ninja.epsilon.physics.Physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class DrinkRenderer implements Renderer {
	
	@SuppressWarnings("unused")
	private Physics refPhysics = null; 
	
	private SpriteBatch spriteBatch = null;
	private Texture texture = null;
	
	private TextureAtlas textureAtlas = null;
	private Animation animation;
	private float elapsedTime = 0;
	
	public DrinkRenderer(final Physics physics) {
		this.refPhysics = physics;
	}


	@Override
	public void create() {
		spriteBatch = new SpriteBatch(); 
		textureAtlas = new TextureAtlas(Gdx.files.internal("drinks" + File.separator + "spritesheet_beer.atlas"));
		animation = new Animation(1/15f, textureAtlas.getRegions());
	}
	

	/* (non-Javadoc)
	 * @see ninja.epsilon.renderers.Renderer#render()
	 */
	@Override
	public void render(SpriteBatch spriteBatch) {
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		

		float xPos = 0;
		//float yPos = (float) (RendererUtils.PixelsPerMeterY()*RendererUtils.PultHeight);
		float yPos = 0;
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		spriteBatch.draw(animation.getKeyFrame(elapsedTime, true), xPos, yPos);
		
	}
	
    @Override
    public void dispose() {
    	spriteBatch.dispose();

		if(texture != null)
			texture.dispose();
		if(textureAtlas != null)
			textureAtlas.dispose();
		
	//	super.dispose();
    }

}

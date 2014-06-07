/**
 * 
 */
package ninja.epsilon.renderers;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ninja.epsilon.drinkers.TypeOfDrink;
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
	
	
	private Map<TypeOfDrink, TextureAtlas> textureIndex = new HashMap<TypeOfDrink, TextureAtlas>();
	private Map<TypeOfDrink, Animation> animationIndex = new HashMap<TypeOfDrink, Animation>();
	
	private SpriteBatch spriteBatch = null;
	private float elapsedTime = 0;
	
	public DrinkRenderer(final Physics physics) {
		this.refPhysics = physics;
	}


	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		
		TypeOfDrink[] values = TypeOfDrink.values();
		TypeOfDrink drink = null;
		int i=0, ilen=values.length;
		TextureAtlas textureAtlas = null;
		Animation animation = null;
		while(i<ilen) {
			drink = values[i];
			textureAtlas = new TextureAtlas(Gdx.files.internal(drink.getPath()));
			textureIndex.put(drink, textureAtlas);
			animation = new Animation(1/15f, textureAtlas.getRegions());
			animationIndex.put(drink, animation);
			++i;
		}
		
		
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
		
		//spriteBatch.draw(animation.getKeyFrame(elapsedTime, true), xPos, yPos);
		
	}
	
    @Override
    public void dispose() {
    	spriteBatch.dispose();
    	

    	Iterator<Entry<TypeOfDrink, TextureAtlas>> it = textureIndex.entrySet().iterator();
    	while (it.hasNext()) 
    		it.next().getValue().dispose();
    	
	//	super.dispose();
    }

}

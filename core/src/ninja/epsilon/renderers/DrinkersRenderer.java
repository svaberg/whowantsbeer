/**
 * 
 */
package ninja.epsilon.renderers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ninja.epsilon.drinkers.Drinker;
import ninja.epsilon.drinkers.Drinker.DrinkerType;
import ninja.epsilon.drinkers.Drinkers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author treestrongs
 *
 */
public class DrinkersRenderer implements Renderer {
	
	private Drinkers DrinkerPool = null;
	private SpriteBatch SpriteBatch = null;
	
	private Map <Integer, Sprite> SpriteMap = new HashMap<Integer, Sprite>();
	
	public DrinkersRenderer(Drinkers Drinkers) {
		
		DrinkerPool = Drinkers; 
	}
	
	@Override
	public void create() {
		SpriteBatch = new SpriteBatch(); 
		
	}
	
    @Override
    public void dispose() {
    	SpriteBatch.dispose();
    }
	
	/* (non-Javadoc)
	 * @see ninja.epsilon.renderers.Renderer#render()
	 */
	@Override
	public void render() {
		
		// Get Drinker List
		List<? extends Drinker> currentDrinkers = DrinkerPool.GetDrinkers();
		
		// Start Rendering Them
		SpriteBatch.begin();
		for (Drinker item : currentDrinkers) {
			
			Sprite Sprite = SpriteMap.get(item.hashCode());
			if (Sprite == null)
			{
				Texture texture = new Texture(Gdx.files.internal(GetTexture(item.GetDrinkerType())));
				Sprite = new Sprite(texture);
				Sprite.setPosition(RendererUtils.PixelsPerMeterX()*item.GetX(),
								   RendererUtils.PixelsPerMeterY()*item.GetY());
				SpriteMap.put(item.hashCode(), Sprite);
			}
		    Sprite.draw(SpriteBatch);
		}
		SpriteBatch.end();
	}
	
	/*
	 * Retrieves the Drinker Texture
	 */
	String GetTexture(DrinkerType Type) {
		
		switch (Type) {
		case SPANISH:
			return "ninja.png";
		case GERMAN:
			return "ninja.png";
		case NORWEGIAN:
			return "ninja.png";
		case POLISH:
			return "ninja.png";
		case ROMANIAN:
			return "ninja.png";
		default:
			return "ninja.png";
		}		
	}
	
}

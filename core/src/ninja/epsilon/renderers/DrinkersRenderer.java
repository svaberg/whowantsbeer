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
import ninja.epsilon.drinkers.TypeOfDrink;

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
				// maximum 3.5 + 0.5 meters on X
				float xPixels = (float) (RendererUtils.PixelsPerMeterX()*(item.getPosition()+0.5));
				// Bar is at 0.5 m height
				float yPixels = (float) (RendererUtils.PixelsPerMeterY()*RendererUtils.PultHeight);
				// Set Position
				Sprite.setPosition(xPixels, yPixels);
				SpriteMap.put(item.hashCode(), Sprite);
				
				// add speak bubble
				//Texture BubbleTexture = new Texture(Gdx.files.internal(GetTextBubble(item.GetDrinkerType())));
				//Sprite Bubble = new Sprite(BubbleTexture);
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
			return "spanish.png";
		case GERMAN:
			return "german.png";
		case NORWEGIAN:
			return "norwegian.png";
		case POLISH:
			return "polish.png";
		case ROMANIAN:
			return "romanian.png";
		default:
			return "ninja.png";
		}		
	}
	
	/*
	 * Retrieves the Drinker Texture
	 */
	String GetTextBubble(TypeOfDrink Type) {
		
		switch (Type) {
		case blondBeer:
			return "blonde.png";
		case darkBeer:
			return "dark.png";
		case tequilaShot:
			return "tequila.png";
		default:
			return "drinks/beer_01.jpg";
		}		
	}
	
}

/**
 * 
 */
package ninja.epsilon.renderers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.Drinker;
import ninja.epsilon.drinkers.Drinker.DrinkerType;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.drinkers.TypeOfDrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ninja.epsilon.Dimensions;

/**
 * @author treestrongs
 *
 */
public class DrinkersRenderer implements Renderer {
	
	private Drinkers DrinkerPool = null;
	
	private Map <Integer, Sprite> SpriteMap = new HashMap<Integer, Sprite>();
	
	public DrinkersRenderer(Drinkers Drinkers) {
		
		DrinkerPool = Drinkers; 
	}
	
	@Override
	public void create() {
	}
	
    @Override
    public void dispose() {
    }
	
	/* (non-Javadoc)
	 * @see ninja.epsilon.renderers.Renderer#render()
	 */
	@Override
	public void render(SpriteBatch spriteBatch) {
		
		// Get Drinker List
		List<? extends Drinker> currentDrinkers = DrinkerPool.GetDrinkers();
		
		// Start Rendering Them
		for (Drinker item : currentDrinkers) {
			
			Sprite Sprite = SpriteMap.get(item.hashCode());
			if (Sprite == null)
			{
				Texture texture = new Texture(Gdx.files.internal(GetTexture(item.GetDrinkerType())));
				Sprite = new Sprite(texture);
				// maximum 3.5 + 0.5 meters on X
				float xPixels = (float) (RendererUtils.PixelsPerMeterX()*item.getPosition()+0.5*Sprite.getWidth());
				// Bar is at 0.5 m height
				float yPixels = (float) (RendererUtils.PixelsPerMeterY()*Dimensions.PULT_HEIGHT);
				// Set Position
				Sprite.setPosition(xPixels, yPixels);
				SpriteMap.put(item.hashCode(), Sprite);
			}
		    Sprite.draw(spriteBatch);
		    
			List<? extends DrinkOrder> drinkOrders = item.getDrinkOrders();
			if (drinkOrders != null)
			{
				// add speak bubble
				Texture BubbleTexture = new Texture(Gdx.files.internal(GetTextBubble(((DrinkOrder) drinkOrders.get(0)).getWhatsTheDrink())));
				Sprite Bubble = new Sprite(BubbleTexture);
				// scale
				Bubble.setScale(0.75f);
				// Set Position
				Bubble.setPosition(Sprite.getX()-20, Sprite.getY()+Sprite.getHeight()-20);
				Bubble.draw(spriteBatch);
			}
		}
		
		// missing feedback of leaving costumers
		if (SpriteMap.size() > 50) {
			SpriteMap.clear();
		}
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
			return "bubble_blondBeer.png";
		case darkBeer:
			return "bubble_blondBeer.png";
		case tequilaShot:
			return "bubble_blondBeer.png";
		default:
			return "bubble_blondBeer.png";
		}
	}
	
}

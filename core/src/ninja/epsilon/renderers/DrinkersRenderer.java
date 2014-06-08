/**
 * 
 */
package ninja.epsilon.renderers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ninja.epsilon.Dimensions;
import ninja.epsilon.drinkers.DrinkOrder;
import ninja.epsilon.drinkers.Drinker;
import ninja.epsilon.drinkers.Drinker.DrinkerType;
import ninja.epsilon.drinkers.Drinkers;
import ninja.epsilon.drinkers.TypeOfDrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * @author treestrongs
 *
 */
public class DrinkersRenderer implements Renderer {

	private Drinkers DrinkerPool = null;
	Sprite Sprite = null;
	Sprite Bubble = null;
	private Map <DrinkerType, Texture> TextureMap = new HashMap<DrinkerType, Texture>();
	private Map <TypeOfDrink, Texture> BubbleTexture = new HashMap<TypeOfDrink, Texture>();


	public DrinkersRenderer(Drinkers Drinkers) {

		DrinkerPool = Drinkers; 
	}

	@Override
	public void create() {

		Sprite = new Sprite();
		Bubble = new Sprite();

		// create texture maps
		TypeOfDrink[] values = TypeOfDrink.values();
		TypeOfDrink drink = null;
		int i=0, ilen=values.length;
		while(i<ilen) {
			drink = values[i];
			BubbleTexture.put(drink, new Texture(Gdx.files.internal(drink.getBubblePath())));
			++i;
		}

		DrinkerType[] drinkers = DrinkerType.values();
		DrinkerType drinker = null;
		i=0; 
		ilen=drinkers.length;
		while(i<ilen) {
			drinker = drinkers[i];
			TextureMap.put(drinker, new Texture(Gdx.files.internal(GetTexture(drinker))));
			++i;
		}
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

			Sprite.setTexture(TextureMap.get(item.GetDrinkerType()));
			// maximum 3.5 + 0.5 meters on X
			float xPixels = (float) (RendererUtils.PixelsPerMeterX()*item.getPosition()+0.5*Sprite.getWidth());
			// Bar is at 0.5 m height
			float yPixels = (float) (RendererUtils.PixelsPerMeterY()*Dimensions.PULT_HEIGHT);
			// Set Position
			Sprite.setPosition(xPixels, yPixels);
			Sprite.draw(spriteBatch);

			List<? extends DrinkOrder> drinkOrders = item.getDrinkOrders();
			if (drinkOrders != null)
			{
				// add speak bubble
				Bubble.setTexture(BubbleTexture.get(drinkOrders.get(0).getWhatsTheDrink()));
				// scale
				Bubble.setScale(0.75f);
				// Set Position
				Bubble.setPosition(Sprite.getX()-20, Sprite.getY()+Sprite.getHeight()-20);
				Bubble.draw(spriteBatch);
			}
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

}

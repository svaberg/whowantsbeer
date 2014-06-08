/**
 * 
 */
package ninja.epsilon.renderers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ninja.epsilon.score.Scorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author treestrongs
 *
 */
public class DashboardRenderer implements Renderer {
	
	private BitmapFont Font = null;
	private Scorer Scorer= null;
	private Texture thumbUpTexture;
	private Texture thumbDownTexture;
	private Sprite thumbUpSprite;
	private Sprite thumbDownSprite;
	
	// Add more types in the future...
	public enum ThumbType {
		THUMB_UP,
		THUMB_DOWN
	};
	
	public DashboardRenderer(Scorer ScorerReference) {
		Scorer = ScorerReference;
	}
	
	@Override
	public void create() {
		Font = new BitmapFont();
		Font.setColor(Color.GREEN);
		
		thumbUpTexture = new Texture(Gdx.files.internal("thumbup2.png"));
		thumbDownTexture =  new Texture(Gdx.files.internal("thumbdown2.png"));
		
		thumbUpSprite = new Sprite(thumbUpTexture);
		thumbDownSprite =  new Sprite(thumbDownTexture);
	}
	
    @Override
    public void dispose() {
    	Font.dispose();
    	thumbUpTexture.dispose();
    	thumbDownTexture.dispose();    	
    }
	
	/* (non-Javadoc)
	 * @see ninja.epsilon.renderers.Renderer#render()
	 */
	@Override
	public void render(SpriteBatch spriteBatch) {
		
		int Fails = Scorer.getNrOfFails();
		int Chances = Scorer.getChances();
		
		// add thumbs up
		for (int i=1; i<Chances; i++)
		{
			thumbUpSprite.setPosition(RendererUtils.PixelsPerMeterX()*0.1f+thumbUpSprite.getWidth()*i, RendererUtils.PixelsPerMeterY()*2.3f);
			thumbUpSprite.draw(spriteBatch);
		}

		// add thumbs down
		for (int i=1; i<Fails; i++)
		{
			thumbDownSprite.setPosition(RendererUtils.PixelsPerMeterX()*0.1f+(thumbDownSprite.getWidth()*(Chances+i)), RendererUtils.PixelsPerMeterY()*2.3f);
			thumbDownSprite.draw(spriteBatch);
		}
		
		// put Dashboard 2.3 meters high and 1.5 meter right
		Font.draw(spriteBatch, "You are awesome! SCORE is: " + Scorer.getScore(), 
				RendererUtils.PixelsPerMeterX()*0.2f+(thumbDownSprite.getWidth()*(Chances+Fails)), 
				RendererUtils.PixelsPerMeterY()*2.4f);
	}	
}

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
	
	// Add more types in the future...
	public enum ThumbType {
		THUMB_UP,
		THUMB_DOWN
	};
	
	// We stored the common used ones...
	private Map<ThumbType, Texture> thumbsIndex = new HashMap<ThumbType, Texture>();
	
	public DashboardRenderer(Scorer ScorerReference) {
		Scorer = ScorerReference;
	}
	
	@Override
	public void create() {
		Font = new BitmapFont();
		Font.setColor(Color.GREEN);
		
		thumbsIndex.put(ThumbType.THUMB_UP, new Texture(Gdx.files.internal("thumbup2.png")));
		thumbsIndex.put(ThumbType.THUMB_DOWN, new Texture(Gdx.files.internal("thumbdown2.png")));
	}
	
    @Override
    public void dispose() {
    	Font.dispose();
    	Iterator<Entry<ThumbType, Texture>> it = thumbsIndex.entrySet().iterator();
    	while(it.hasNext())
    		it.next().getValue().dispose();
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
			// add thumbs up
			Sprite ThumbsUp = new Sprite(thumbsIndex.get(ThumbType.THUMB_UP));
			// Set Position
			ThumbsUp.setPosition(RendererUtils.PixelsPerMeterX()*0.1f+ThumbsUp.getWidth()*i, RendererUtils.PixelsPerMeterY()*2.3f);
			ThumbsUp.draw(spriteBatch);
		}

		// add thumbs down
		for (int i=1; i<Fails; i++)
		{
			// add thumbs down
			Sprite ThumbsDown =  new Sprite(thumbsIndex.get(ThumbType.THUMB_DOWN));
			// Set Position
			ThumbsDown.setPosition(RendererUtils.PixelsPerMeterX()*0.1f+(ThumbsDown.getWidth()*(Chances+i)), RendererUtils.PixelsPerMeterY()*2.3f);
			ThumbsDown.draw(spriteBatch);
		}
		
		Sprite Thumbs = new Sprite(thumbsIndex.get(ThumbType.THUMB_DOWN));
		
		// put Dashboard 2.3 meters high and 1.5 meter right
		Font.draw(spriteBatch, "You are awesome! SCORE is: " + Scorer.getScore(), 
				RendererUtils.PixelsPerMeterX()*0.2f+(Thumbs.getWidth()*(Chances+Fails)), 
				RendererUtils.PixelsPerMeterY()*2.4f);
	}
	
	
	
}

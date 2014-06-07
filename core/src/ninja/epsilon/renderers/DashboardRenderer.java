/**
 * 
 */
package ninja.epsilon.renderers;

import ninja.epsilon.drinkers.DrinkOrder;
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
	
	public DashboardRenderer(Scorer ScorerReference) {
		Scorer = ScorerReference;
	}
	
	@Override
	public void create() {
		Font = new BitmapFont();
		Font.setColor(Color.GREEN);
	}
	
    @Override
    public void dispose() {
    	Font.dispose();
    }
	
	/* (non-Javadoc)
	 * @see ninja.epsilon.renderers.Renderer#render()
	 */
	@Override
	public void render(SpriteBatch spriteBatch) {
		
		// Start Rendering Them

		// set font
		
		// put Dashboard 2.3 meters high and 1.5 meter right
		Font.draw(spriteBatch, "You are awesome! SCORE is: " + Scorer.getScore(), 
				RendererUtils.PixelsPerMeterX()*1.3f, 
				RendererUtils.PixelsPerMeterY()*2.4f);
		
		int Fails = Scorer.getNrOfFails();
		int Chances = Scorer.getChances();
		
		// add thumbs up
		Texture ThumbsupTexture = new Texture(Gdx.files.internal("thumbup2.png"));
		for (int i=1; i<Chances; i++)
		{
			// add thumbs up
			Sprite ThumbsUp = new Sprite(ThumbsupTexture);
			// Set Position
			ThumbsUp.setPosition(RendererUtils.PixelsPerMeterX()*0.1f+ThumbsUp.getWidth()*i, RendererUtils.PixelsPerMeterY()*2.3f);
			ThumbsUp.draw(spriteBatch);
		}

		// add thumbs down
		Texture ThumbsDownTexture = new Texture(Gdx.files.internal("thumbdown2.png"));
		for (int i=1; i<Fails; i++)
		{
			// add thumbs up
			Sprite ThumbsDown = new Sprite(ThumbsDownTexture);
			// Set Position
			ThumbsDown.setPosition(RendererUtils.PixelsPerMeterX()*0.1f+(ThumbsDown.getWidth()*(Chances+i)), RendererUtils.PixelsPerMeterY()*2.3f);
			ThumbsDown.draw(spriteBatch);
		}
	}
	
}

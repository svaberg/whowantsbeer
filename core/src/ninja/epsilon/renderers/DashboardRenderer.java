/**
 * 
 */
package ninja.epsilon.renderers;

import ninja.epsilon.score.Scorer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author treestrongs
 *
 */
public class DashboardRenderer implements Renderer {
	
	private SpriteBatch SpriteBatch = null;
	private BitmapFont Font = null;
	private Scorer Scorer= null;
	
	public DashboardRenderer(Scorer ScorerReference) {
		Scorer = ScorerReference;
	}
	
	@Override
	public void create() {
		SpriteBatch = new SpriteBatch(); 
		Font = new BitmapFont();
		Font.setColor(Color.BLUE);
	}
	
    @Override
    public void dispose() {
    	SpriteBatch.dispose();
    	Font.dispose();
    }
	
	/* (non-Javadoc)
	 * @see ninja.epsilon.renderers.Renderer#render()
	 */
	@Override
	public void render() {
		
		// Start Rendering Them
		SpriteBatch.begin();

		// put Dashboard 2.3 meters high and 1.5 meter right
		Font.draw(SpriteBatch, "You are awesome! SCORE is: " + Scorer.getScore(), 
				RendererUtils.PixelsPerMeterX()*1.5f, 
				RendererUtils.PixelsPerMeterY()*2.3f);
		SpriteBatch.end();
	}
	
}

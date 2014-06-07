/**
 * 
 */
package ninja.epsilon.renderers;

import java.util.List;

import ninja.epsilon.drinkers.Drinker;
import ninja.epsilon.drinkers.Drinkers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author treestrongs
 *
 */
public class DrinkersRenderer implements Renderer {
	
	private Drinkers DrinkerPool;
	private SpriteBatch SpriteBatch;
	
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
		// TODO Auto-generated method stub
		
		List<? extends Drinker> currentDrinkers = DrinkerPool.GetDrinkers();
		
		for (Drinker item : currentDrinkers) {
		    System.out.println(item);
		}

	}

}

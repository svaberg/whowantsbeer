
package ninja.epsilon;

import com.badlogic.gdx.Game;



public class WhoWantsBeerGame extends Game {
	

    public void create() {
		this.setScreen(new SplashScreen(this));
		// Created now in splahs screen
//		if(Gdx.input.justTouched())
//			this.setScreen(new GameScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
    	super.dispose();
    }
    
}



package ninja.epsilon;

//import java.awt.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverScreen implements Screen {

	final WhoWantsBeerGame game;
	private Stage stage;
	private Skin skin;
	
	GameOverScreen(final WhoWantsBeerGame game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		stage.act();
	    stage.draw();	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		skin = new Skin();
  
        
//		Table table = new Table(ResPack._SKIN);
//	 
//	    table.setFillParent(true);
//	    table.center();
//	 
//	    Label title = new Label("MAIN MENU", ResPack._SKIN);
//	    title.setFontScale(2.5f);
//	 
//	    TextButton resume = new TextButton("START GAME", ResPack._SKIN);
//	    TextButton options = new TextButton("OPTIONS", ResPack._SKIN);
//	    TextButton  exit = new TextButton("QUIT GAME", ResPack._SKIN);
//	 

//	        resume.addListener(new ClickListener() {
//	     
//	          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//	            parent.setupInput();
//	            parent.setScreen(null);
//	          }
//	        });
	    
//	    table.add(title).center().pad(25f);
//	    table.row().height(75);
//	    table.add(resume).center().width(500).pad(5f);
//	    table.row().height(75);
//	    table.add(options).center().width(500).pad(5f);
//	    table.row().height(75);
//	    table.add(exit).center().width(500).pad(5f);
//	    stage.addActor(table);
	    Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

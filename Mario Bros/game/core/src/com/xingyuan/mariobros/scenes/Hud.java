/**
 * 
 */
package com.xingyuan.mariobros.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xingyuan.mariobros.MarioBros;

/**
 * @author 星缘
 *
 * 2018年5月10日
 */
public class Hud implements Disposable{
	
	public Stage stage;
	private Viewport viewport;
	
	
	private Integer worldTimer;
	
	private float timeCount;
	
	private Integer score;
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label marioLabel;
	
	public Hud(SpriteBatch batch) {
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT,new OrthographicCamera());
		
		stage = new Stage(viewport,batch);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		countdownLabel = new Label(String.format("%03d", worldTimer)
				,new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		scoreLabel = new Label(String.format("%06d", score)
				,new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		levelLabel = new Label("1-1"
				,new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		
		timeLabel = new Label("TIME"
				,new Label.LabelStyle(new BitmapFont(),Color.WHITE));
	
		worldLabel = new Label("WORLD"
				,new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		marioLabel = new Label("MARIO"
				,new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
	
		table.add(marioLabel).expandX().top();
		table.add(worldLabel).expandX().top();
		table.add(timeLabel).expandX().top();
		table.row();
		table.add(scoreLabel).expandX().top();
		table.add(levelLabel).expandX().top();
		table.add(countdownLabel).expand().top();
		
		stage.addActor(table);
		
	}


	@Override
	public void dispose() {
		stage.dispose();
		
	}
	

}

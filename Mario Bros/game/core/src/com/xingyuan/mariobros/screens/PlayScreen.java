/**
 * 
 */
package com.xingyuan.mariobros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xingyuan.mariobros.scenes.Hud;
import com.xingyuan.mariobros.sprites.Mario;
import com.xingyuan.mariobros.tools.B2WorldCreator;
import com.xingyuan.mariobros.MarioBros;

/**
 * @author 星缘
 *
 *         2018年5月9日
 */
public class PlayScreen implements Screen {

	private MarioBros game;
	//怪物 玩家资源图片
	private TextureAtlas atlas;
	
	private OrthographicCamera gameCamera;

	private Viewport gamePort;

	private Hud hud;
	// map
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	// box2d
	private World world;
	private Box2DDebugRenderer box2dDebugRenderer;
	private Mario mario;
	
	public PlayScreen(MarioBros game) {
		atlas = new TextureAtlas("Mario_and_Enemies.pack");
		
		this.game = game;
		gameCamera = new OrthographicCamera();
		gamePort = new FitViewport(MarioBros.V_WIDTH/MarioBros.PPM, MarioBros.V_HEIGHT/MarioBros.PPM, gameCamera);

		hud = new Hud(game.batch);

		mapLoader = new TmxMapLoader();
		map = mapLoader.load("level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map,1/MarioBros.PPM);

		gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		// box2d
		world = new World(new Vector2(0, -10), true);

		box2dDebugRenderer = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		//游戏角色
		mario = new Mario(world,this);
		
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public void handleInput(float dt) {
		//方向键上
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			mario.b2body.applyLinearImpulse(new Vector2(0,4f), mario.b2body.getWorldCenter(), true);
		}
		//方向右键
		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)
				&& mario.b2body.getLinearVelocity().x <= 2) {
			mario.b2body.applyLinearImpulse(new Vector2(1f,0), mario.b2body.getWorldCenter(), true);
		}
		//方向左键
		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)
				&& mario.b2body.getLinearVelocity().x >= -2) {
			mario.b2body.applyLinearImpulse(new Vector2(-1f,0), mario.b2body.getWorldCenter(), true);
		}
		
		
	}

	public void update(float dt) {
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		
		mario.update(dt);
		
		gameCamera.position.x = mario.b2body.getPosition().x;
		
		gameCamera.update();

		renderer.setView(gameCamera);
	}

	@Override
	public void render(float delta) {

		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();
		//box2d
		box2dDebugRenderer.render(world, gameCamera.combined);
		
		
		game.batch.setProjectionMatrix(gameCamera.combined);
		game.batch.begin();
		mario.draw(game.batch);
		game.batch.end();
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);

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
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		world.dispose();
		box2dDebugRenderer.dispose();
		hud.dispose();
		// TODO Auto-generated method stub

	}

}

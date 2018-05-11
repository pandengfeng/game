/**
 * 
 */
package com.brentaureli.mariobros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.brentaureli.mariobros.MarioBros;
import com.brentaureli.mariobros.scenes.Hud;

/**
 * @author 星缘
 *
 *         2018年5月9日
 */
public class PlayScreen implements Screen {

	private MarioBros game;

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

	public PlayScreen(MarioBros game) {
		this.game = game;
		gameCamera = new OrthographicCamera();
		gamePort = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, gameCamera);

		hud = new Hud(game.batch);

		mapLoader = new TmxMapLoader();
		map = mapLoader.load("Mario Bros.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);

		gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		// box2d
		world = new World(new Vector2(0, 0), true);
		box2dDebugRenderer = new Box2DDebugRenderer();

		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		Body body;
		// ground
		for (MapObject mapObject : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			// 静态物体
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

			body = world.createBody(bodyDef);

			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
		// pipes
		for (MapObject mapObject : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			// 静态物体
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

			body = world.createBody(bodyDef);

			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}

		// bricks
		for (MapObject mapObject : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			// 静态物体
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

			body = world.createBody(bodyDef);

			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
		// coins
		for (MapObject mapObject : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			// 静态物体
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

			body = world.createBody(bodyDef);

			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public void handleInput(float dt) {
		if (Gdx.input.isTouched()) {
			gameCamera.position.x += 100 * dt;
		}
	}

	public void update(float dt) {
		handleInput(dt);

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
		// TODO Auto-generated method stub

	}

}

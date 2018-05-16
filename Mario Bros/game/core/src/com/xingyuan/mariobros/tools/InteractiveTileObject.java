/**
 * 
 */
package com.xingyuan.mariobros.tools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.xingyuan.mariobros.MarioBros;

/**
 * @author 星缘
 *
 * 2018年5月15日
 */
public abstract class InteractiveTileObject {
	
	protected World world;
	
	protected TiledMap map;
	
	protected TiledMapTile mapTile;
	
	protected Rectangle rectangle;
	
	protected Body body;

	/**
	 * @author 星缘
	 * 下午2:10:28
	 */
	public InteractiveTileObject(World world, TiledMap map, Rectangle rectangle) {
		this.world = world;
		this.map = map;
		this.rectangle = rectangle;
		
		
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape polygonShape = new PolygonShape();

		// 静态物体
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MarioBros.PPM,
				(rectangle.getY() + rectangle.getHeight() / 2) / MarioBros.PPM);

		body = world.createBody(bodyDef);

		polygonShape.setAsBox(rectangle.getWidth() / 2 / MarioBros.PPM, rectangle.getHeight() / 2 / MarioBros.PPM);
		fixtureDef.shape = polygonShape;
		body.createFixture(fixtureDef);
	}
	
	
}

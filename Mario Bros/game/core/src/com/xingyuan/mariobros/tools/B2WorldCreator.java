/**
 * 
 */
package com.xingyuan.mariobros.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
public class B2WorldCreator {
	
	public B2WorldCreator(World world,TiledMap map) {
		
		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		Body body;
		// ground
		for (MapObject mapObject : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			// 静态物体
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set((rect.getX() + rect.getWidth() / 2)/MarioBros.PPM, (rect.getY() + rect.getHeight() / 2)/MarioBros.PPM);

			body = world.createBody(bodyDef);

			shape.setAsBox(rect.getWidth() / 2/MarioBros.PPM, rect.getHeight() / 2/MarioBros.PPM);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
		// pipes
		for (MapObject mapObject : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			// 静态物体
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set((rect.getX() + rect.getWidth() / 2)/MarioBros.PPM, (rect.getY() + rect.getHeight() / 2)/MarioBros.PPM);

			body = world.createBody(bodyDef);

			shape.setAsBox(rect.getWidth() / 2/MarioBros.PPM, rect.getHeight() / 2/MarioBros.PPM);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}

		// bricks
		for (MapObject mapObject : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			new Brick(world, map, rect);
		}
		// coins
		for (MapObject mapObject : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
			new Coin(world, map, rect);
		}
	}
}

/**
 * 
 */
package com.xingyuan.mariobros.tools;

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
 *         2018年5月15日
 */
public class Coin extends InteractiveTileObject {

	/**
	 * @author 星缘 下午2:10:58
	 */
	public Coin(World world, TiledMap map, Rectangle rectangle) {
		super(world, map, rectangle);

		
	}

}

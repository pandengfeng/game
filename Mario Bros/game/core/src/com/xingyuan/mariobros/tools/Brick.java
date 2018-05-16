/**
 * 
 */
package com.xingyuan.mariobros.tools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author 星缘
 *
 * 2018年5月15日
 */
public class Brick extends InteractiveTileObject{

	/**
	 * @author 星缘
	 * 下午2:17:49
	 */
	public Brick(World world, TiledMap map, Rectangle rectangle) {
		super(world, map, rectangle);
	}

}

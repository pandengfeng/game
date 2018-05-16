/**
 * 
 */
package com.xingyuan.mariobros.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.xingyuan.mariobros.screens.PlayScreen;
import com.xingyuan.mariobros.MarioBros;

/**
 * @author 星缘
 *
 * 2018年5月15日
 */
public class Mario extends Sprite{
	
	public enum State{FALLING,JUMPING,STANDING,RUNNING};
	
	public State currentState;
	public State previousState;
	
	
	public World world;
	public Body b2body;
	private TextureRegion mairoStand;
	
	private Animation<TextureRegion> marioRun;
	private Animation<TextureRegion> marioJump;
	
	private float stateTimer;
	private boolean runningRight;
	
 	
	public Mario(World world,PlayScreen screen) {
		super(screen.getAtlas().findRegion("little_mario"));
		this.world = world;
		
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for (int i = 1; i < 4; i++) {
			frames.add(new TextureRegion(getTexture(),i*16,11,16,16));
		}
		
		marioRun = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
		
		for (int i = 4; i < 6; i++) {
			frames.add(new TextureRegion(getTexture(),i*16,11,16,16));
		}
		marioJump = new Animation<TextureRegion>(0.1f,frames);
		
		
		defineMario();
		mairoStand = new TextureRegion(getTexture(),1,11,16,16);
		
		setBounds(0,0, 16/MarioBros.PPM, 16/MarioBros.PPM);
		setRegion(mairoStand);
	}
	
	public void update(float fd) {
		setPosition(b2body.getPosition().x -getWidth()/2, b2body.getPosition().y - getHeight()/2);
		setRegion(getFrame(fd));
	}
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch (currentState) {
		case JUMPING:
			region = marioJump.getKeyFrame(stateTimer);
			break;
		case RUNNING:
			region = marioRun.getKeyFrame(stateTimer,true);
			break;
		case FALLING:
		case STANDING:
		default:
			region = mairoStand;
			break;
		}
		
		if((b2body.getLinearVelocity().x < 0 || !runningRight) &&
				!region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}else if((b2body.getLinearVelocity().x >0 || runningRight) &&
				region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		
		previousState = currentState;
		return region;
	}
	
	
	public State getState() {
		//jump
		if(b2body.getLinearVelocity().y >0 ||
				(b2body.getLinearVelocity().y<0 && previousState == State.JUMPING)) {
			return State.JUMPING;
		}
		//fail
		else if(b2body.getLinearVelocity().y <0) {
			return State.FALLING;
		}
		
		else if(b2body.getLinearVelocity().x !=0) {
			return State.RUNNING;
		}
		
		else {
			return State.STANDING;
		}		
		
	}
	
	public void defineMario() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32/MarioBros.PPM,32/MarioBros.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(6/MarioBros.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);

	}
}

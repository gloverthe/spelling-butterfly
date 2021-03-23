package com.glover.spellingbutterfly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;


public class SpellingButterfly extends ApplicationAdapter {


//	Background images
	private Texture flowerBackground1, flowerBackground2;
	float xMax, xMin, xCoordBg1, xCoordBg2;
	final int BACKGROUND_MOVE_SPEED = -100; // pixels per second. Put your value here.



//	Other
	private SpriteBatch batch;
	private OrthographicCamera camera;



	@Override
	public void create () {

		Butterfly.load();
		Flowers.load();


//		create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,  Screen.SCREEN_WIDTH , Screen.SCREEN_HEIGHT);

		batch = new SpriteBatch();


//		create background
		flowerBackground1 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg"));
		flowerBackground2 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg")); // identical
		xMax = Screen.SCREEN_WIDTH;
		xMin = 0;
		xCoordBg1 = xMin; xCoordBg2 = Screen.SCREEN_WIDTH;


	}


	@Override
	public void render() {

		float deltaTime = Gdx.graphics.getDeltaTime();
		Butterfly.butterflyStateTime += deltaTime;



//		Render Background
		xCoordBg1 += BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
		xCoordBg2 = xCoordBg1 + xMax;  // We move the background, not the camera
		if (xCoordBg1 <= - Screen.SCREEN_WIDTH ) {
			xCoordBg1 = xMin; xCoordBg2 = Screen.SCREEN_WIDTH;
		}


		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(flowerBackground1, xCoordBg1, 0);
		batch.draw(flowerBackground2, xCoordBg2, 0);
		batch.draw(Butterfly.butterflySprite.getKeyFrame(Butterfly.butterflyStateTime), Butterfly.butterfly.x, Butterfly.butterfly.y);

		for(Rectangle pinkFlower_1: Flowers.flowers) {
				batch.draw(Flowers.flowerToDraw, pinkFlower_1.x, pinkFlower_1.y);
		}

//		DEBUG LINEs
//		System.out.println("X BG1 = " + xCoordBg1 + ", X BG2 = " + xCoordBg2);
//		System.out.println("Which flower is " + whichFlower + " and flowerToDraw is : " + flowerToDraw);
		batch.end();

		if(Gdx.input.isTouched()) {
			//need to fix input so touch does not send it crazy
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			butterfly.y = touchPos.y - (190 / 2);
			Butterfly.butterfly.y += 2000 * Gdx.graphics.getDeltaTime();
			Butterfly.wingFlap.play();
		}

		if(Gdx.input.isKeyPressed(Input.Keys.UP)) Butterfly.butterfly.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) Butterfly.butterfly.y -= 200 * Gdx.graphics.getDeltaTime();

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			Butterfly.butterfly.y += 2000 * Gdx.graphics.getDeltaTime();
			Butterfly.wingFlap.play();
		}

		Butterfly.butterfly.y += BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
		if(Butterfly.butterfly.y < 0) Butterfly.butterfly.y = 0;
		if(Butterfly.butterfly.y > Screen.SCREEN_HEIGHT - 190) Butterfly.butterfly.y =  Screen.SCREEN_HEIGHT - 190;


//		flowers
		if(TimeUtils.nanoTime() - Flowers.lastFlowerTime > 1000000000) Flowers.spawnFlower();

		for (Iterator<Rectangle> iter = Flowers.flowers.iterator(); iter.hasNext(); ) {
			Rectangle pinkFlower_1 = iter.next();
			pinkFlower_1.x -= 200 * Gdx.graphics.getDeltaTime();
			if(pinkFlower_1.x  < (0 - Flowers.pinkFlower_1_Width)) iter.remove();

		if(pinkFlower_1.overlaps(Butterfly.butterfly)) {
			Flowers.flowerPop.play();
			iter.remove();
			}
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		flowerBackground1.dispose();
		flowerBackground2.dispose();
		Flowers.pinkFlower_1_Sprite.dispose();
		Flowers.pinkFlower_2_Sprite.dispose();
		Butterfly.butterflySprite_1.dispose();
		Butterfly.butterflySprite_2.dispose();
		Butterfly.butterflySprite_3.dispose();

	}
}

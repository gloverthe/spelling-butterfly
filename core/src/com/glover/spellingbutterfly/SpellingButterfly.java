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

//	butterfly
//	private Texture butterflySprite_1;
//	private Texture butterflySprite_2;
//	private Texture butterflySprite_3;
//	private Sound wingFlap;
//	private Rectangle butterfly;
//	Animation<TextureRegion> butterflySprite;
//	float butterflyStateTime = 0;
//	final int BUTTERFLY_FALL_SPEED = -110;

//	Screen Dimensions
	int screenHeight = 1280;
	int screenWidth = 1920;

//	Flowers
	private Texture pinkFlower_1_Sprite;
	private Texture pinkFlower_2_Sprite;
	private Texture flowerToDraw;
	private Sound flowerPop;
	private Array<Rectangle> flowers;
	private long lastFlowerTime;
	private int pinkFlower_1_Height = 146;
	private int pinkFlower_1_Width = 110;
	private int pinkFlower_2_Height = 100;
	private int pinkFlower_2_Width = 100;

//	Background images
	private Texture flowerBackground1, flowerBackground2;
	float xMax, xMin, xCoordBg1, xCoordBg2;
	final int BACKGROUND_MOVE_SPEED = -100; // pixels per second. Put your value here.


//	Sounds

//	Other
	private SpriteBatch batch;
	private OrthographicCamera camera;



	@Override
	public void create () {



////		create butterfly
//		butterflySprite_1 = new Texture(Gdx.files.internal("Butterfly_1.png"));
//		butterflySprite_2 = new Texture(Gdx.files.internal("Butterfly_3.png"));
//		butterflySprite_3 = new Texture(Gdx.files.internal("Butterfly_2.png"));
//
//		butterflySprite_1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//		butterflySprite = 	new Animation<TextureRegion>(0.05f,
//							new TextureRegion(butterflySprite_1),
//							new TextureRegion(butterflySprite_2),
//							new TextureRegion(butterflySprite_3),
//							new TextureRegion(butterflySprite_1));
//		butterflySprite.setPlayMode(Animation.PlayMode.LOOP);
//
//
//		wingFlap = Gdx.audio.newSound(Gdx.files.internal("butterflyFlapPCM.wav"));
//
//		butterfly = new Rectangle();
//		butterfly.x = 20;
//		butterfly.y = (screenHeight / 2) - (butterfly.height / 2);
//		butterfly.width = 205;
//		butterfly.height = 190;

		flowerPop = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));

		Butterfly.load();




//		create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,  screenWidth , screenHeight);

		batch = new SpriteBatch();


//		create background
		flowerBackground1 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg"));
		flowerBackground2 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg")); // identical
		xMax = screenWidth;
		xMin = 0;
		xCoordBg1 = xMin; xCoordBg2 = screenWidth;


//		create the flowers
		pinkFlower_1_Sprite	= new Texture(Gdx.files.internal("pinkFlower_2.png"));
		pinkFlower_2_Sprite	= new Texture(Gdx.files.internal("pinkFlower_1.png"));

		flowers = new Array<Rectangle>();
		spawnFlower();


	}

	private void spawnFlower() {
		boolean whichFlower = MathUtils.randomBoolean();
		if (whichFlower == true) {

			Rectangle pinkFlower_1 = new Rectangle();
			pinkFlower_1.x = screenWidth;
			pinkFlower_1.y = MathUtils.random(0, screenHeight-pinkFlower_1_Height);
			pinkFlower_1.width = pinkFlower_1_Width;
			pinkFlower_1.height = pinkFlower_1_Height;
			flowers.add(pinkFlower_1);
			flowerToDraw = pinkFlower_1_Sprite;
			lastFlowerTime = TimeUtils.nanoTime();

		}
		if (whichFlower == false) {
			Rectangle pinkFlower_1 = new Rectangle();
			pinkFlower_1.x = screenWidth;
			pinkFlower_1.y = MathUtils.random(0, screenHeight - pinkFlower_2_Height);
			pinkFlower_1.width = pinkFlower_2_Width;
			pinkFlower_1.height = pinkFlower_2_Height;
			flowers.add(pinkFlower_1);
			flowerToDraw = pinkFlower_2_Sprite;
			lastFlowerTime = TimeUtils.nanoTime();
		}
	}

	@Override
	public void render() {

		float deltaTime = Gdx.graphics.getDeltaTime();
		Butterfly.butterflyStateTime += deltaTime;



//		Render Background
		xCoordBg1 += BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
		xCoordBg2 = xCoordBg1 + xMax;  // We move the background, not the camera
		if (xCoordBg1 <= -screenWidth ) {
			xCoordBg1 = xMin; xCoordBg2 = screenWidth;
		}


		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(flowerBackground1, xCoordBg1, 0);
		batch.draw(flowerBackground2, xCoordBg2, 0);
		batch.draw(Butterfly.butterflySprite.getKeyFrame(Butterfly.butterflyStateTime), Butterfly.butterfly.x, Butterfly.butterfly.y);

		for(Rectangle pinkFlower_1: flowers) {
				batch.draw(flowerToDraw, pinkFlower_1.x, pinkFlower_1.y);
		}

		System.out.println("X BG1 = " + xCoordBg1 + ", X BG2 = " + xCoordBg2);
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
		if(Butterfly.butterfly.y > screenHeight - 190) Butterfly.butterfly.y =  screenHeight - 190;


//		flowers
		if(TimeUtils.nanoTime() - lastFlowerTime > 1000000000) spawnFlower();

		for (Iterator<Rectangle> iter = flowers.iterator(); iter.hasNext(); ) {
			Rectangle pinkFlower_1 = iter.next();
			pinkFlower_1.x -= 200 * Gdx.graphics.getDeltaTime();
			if(pinkFlower_1.x  < (0 - pinkFlower_1_Width)) iter.remove();

		if(pinkFlower_1.overlaps(Butterfly.butterfly)) {
			flowerPop.play();
			iter.remove();
			}
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		flowerBackground1.dispose();
		flowerBackground2.dispose();
		pinkFlower_1_Sprite.dispose();
	}
}

package com.glover.spellingbutterfly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;


public class SpellingButterfly extends ApplicationAdapter {

//	butterfly
	private Texture butterflySprite;
	private Sound wingFlap;
	private Rectangle butterfly;
	final int BUTTERFLY_FALL_SPEED = -110;

//	Screen Dimensions
	int screenHeight = 1280;
	int screenWidth = 1920;

//	Flowers
	private Texture pinkFlower_1_Sprite;
	private Texture pinkFlower_2_Sprite;
	private Sound flowerPop;
	private Array<Rectangle> flowers;
	private long lastFlowerTime;
	private int pinkFlower_1_Height = 146;
	private int pinkFlower_1_Width = 110;
	private int pinkFlower_2_Height = 101;
	private int pinkFlower_2_Width = 101;

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
//		create butterfly
		butterflySprite = new Texture(Gdx.files.internal("butterfly-removebg.png"));
		wingFlap = Gdx.audio.newSound(Gdx.files.internal("butterflyFlapPCM.wav"));
		flowerPop = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));
		butterfly = new Rectangle();
		butterfly.x = 20;
		butterfly.y = (screenHeight / 2) - (butterfly.height / 2);
		butterfly.width = 217;
		butterfly.height = 190;

//		Butterfly.create();




//		create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,  screenWidth , screenHeight);

		batch = new SpriteBatch();


//		create background
		flowerBackground1 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg"));
		flowerBackground2 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg")); // identical
		xMax = 1920;
		xMin = 0;
		xCoordBg1 = xMin; xCoordBg2 = 1920;


//		create the flowers
		pinkFlower_1_Sprite	= new Texture(Gdx.files.internal("pinkFlower_1.png"));
		pinkFlower_2_Sprite	= new Texture(Gdx.files.internal("pinkFlower_2.png"));

		flowers = new Array<Rectangle>();
		spawnFlower();


	}

	private void spawnFlower() {
		Rectangle pinkFlower_1 = new Rectangle();
		pinkFlower_1.x = 1920;
		pinkFlower_1.y = MathUtils.random(0, 1280-pinkFlower_1_Height);
		pinkFlower_1.width = pinkFlower_1_Width;
		pinkFlower_1.height = pinkFlower_1_Height;
		flowers.add(pinkFlower_1);
		lastFlowerTime = TimeUtils.nanoTime();
	}

	@Override
	public void render () {



//		Render Background
		xCoordBg1 += BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
		xCoordBg2 = xCoordBg1 + xMax;  // We move the background, not the camera
		if (xCoordBg1 <= -1920 ) {
			xCoordBg1 = xMin; xCoordBg2 = 1920;
		}


		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(flowerBackground1, xCoordBg1, 0);
		batch.draw(flowerBackground2, xCoordBg2, 0);
		batch.draw(butterflySprite, butterfly.x, butterfly.y);
		for(Rectangle pinkFlower_1: flowers) {
			batch.draw(pinkFlower_1_Sprite, pinkFlower_1.x, pinkFlower_1.y);
		}

		System.out.println("X BG1 = " + xCoordBg1 + ", X BG2 = " + xCoordBg2);
		batch.end();

		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			butterfly.y = touchPos.y - (190 / 2);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.UP)) butterfly.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) butterfly.y -= 200 * Gdx.graphics.getDeltaTime();

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			butterfly.y += 2000 * Gdx.graphics.getDeltaTime();
			wingFlap.play();
		}

		butterfly.y += BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
		if(butterfly.y < 0) butterfly.y = 0;
		if(butterfly.y > 1280 - 190) butterfly.y =  1280 - 190;


//		flowers
		if(TimeUtils.nanoTime() - lastFlowerTime > 1000000000) spawnFlower();

		for (Iterator<Rectangle> iter = flowers.iterator(); iter.hasNext(); ) {
			Rectangle pinkFlower_1 = iter.next();
			pinkFlower_1.x -= 200 * Gdx.graphics.getDeltaTime();
			if(pinkFlower_1.x  < (0 - pinkFlower_1_Width)) iter.remove();

		if(pinkFlower_1.overlaps(butterfly)) {
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
		butterflySprite.dispose();
	}
}

package com.glover.spellingbutterfly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpellingButterfly extends ApplicationAdapter {

//	butterfly assets
	private Texture butterfly;
	private Sound wingFlap;


//	Flowers
	private Texture pinkFlower;

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
		butterfly = new Texture(Gdx.files.internal("butterfly.png"));
//		wingFlap = Gdx.audio.newSound(Gdx.files.internal("butterflyFlap.wav"));

//		create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1200);

		batch = new SpriteBatch();


//		create background
		flowerBackground1 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg"));
		flowerBackground2 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg")); // identical
		xMax = 1920;
		xMin = 0;
		xCoordBg1 = xMin; xCoordBg2 = 1920;
	}

	@Override
	public void render () {



//		Render Background
		xCoordBg1 += BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
		xCoordBg2 = xCoordBg1 + xMax;  // We move the background, not the camera
		if (xCoordBg1 <= -1920 ) {
			xCoordBg1 = xMin; xCoordBg2 = 1920;
		}



		batch.begin();
		batch.draw(flowerBackground1, xCoordBg1, 0);
		batch.draw(flowerBackground2, xCoordBg2, 0);
		System.out.println("X BG1 = " + xCoordBg1 + ", X BG2 = " + xCoordBg2);
		batch.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		flowerBackground1.dispose();
		flowerBackground2.dispose();
	}
}

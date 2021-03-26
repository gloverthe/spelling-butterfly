package com.glover.spellingbutterfly;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class SpellingButterfly extends Game {
	SpriteBatch batch;
	BitmapFont font;
	MainGame mainGame;
	HiddenWordGame hiddenWordGame;
//	OrthographicCamera camera;


	public void create() {
		batch = new SpriteBatch();
//		 Use LibGDX's default Arial font.
		font = new BitmapFont(); // use libGDX's default Arial font
		font.getData().setScale(6, 6);
//		batch = new SpriteBatch();

		mainGame = new MainGame(this);
//		hiddenWordGame = new HiddenWordGame(this);
//		camera = new OrthographicCamera();

		setScreen(mainGame);

	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		Background.flowerBackground1.dispose();
		Background.flowerBackground2.dispose();
		Flowers.pinkFlower_1_Sprite.dispose();
		Flowers.pinkFlower_2_Sprite.dispose();
		Butterfly.butterflySprite_1.dispose();
		Butterfly.butterflySprite_2.dispose();
		Butterfly.butterflySprite_3.dispose();
	}

}



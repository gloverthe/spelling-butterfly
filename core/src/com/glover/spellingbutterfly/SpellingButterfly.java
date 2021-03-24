package com.glover.spellingbutterfly;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;




public class SpellingButterfly extends Game {
	SpriteBatch batch;
	BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont(); // use libGDX's default Arial font
		font.getData().setScale(6, 6);
		batch = new SpriteBatch();
		this.setScreen(new MainGame(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}



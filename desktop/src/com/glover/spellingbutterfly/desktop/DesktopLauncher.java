package com.glover.spellingbutterfly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.glover.spellingbutterfly.SpellingButterfly;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Spelling Butterfly";
		config.width = 1920;
		config.height = 1200;

		new LwjglApplication(new SpellingButterfly(), config);
	}
}

package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Background {
    //	Background images
    public static Texture flowerBackground1, flowerBackground2;
    public static float xMax, xMin, xCoordBg1, xCoordBg2;
    public static final int BACKGROUND_MOVE_SPEED = -100; // pixels per second.

    public static void load() {
        //		create background
        flowerBackground1 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg"));
        flowerBackground2 = new Texture(Gdx.files.internal("FlowersBackground_1920x1280.jpg")); // identical
        xMax = Screen.SCREEN_WIDTH;
        xMin = 0;
        xCoordBg1 = xMin; xCoordBg2 = Screen.SCREEN_WIDTH;



    }
}

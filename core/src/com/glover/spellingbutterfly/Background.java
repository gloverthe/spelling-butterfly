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
        xMax = Constants.SCREEN_WIDTH;
        xMin = 0;
        xCoordBg1 = xMin; xCoordBg2 = Constants.SCREEN_WIDTH;



    }

    public static void renderBackground(){
        Background.xCoordBg1 += Background.BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
        Background.xCoordBg2 = Background.xCoordBg1 + Background.xMax;  // We move the background, not the camera
        if (Background.xCoordBg1 <= - com.glover.spellingbutterfly.Constants.SCREEN_WIDTH ) {
            Background.xCoordBg1 = Background.xMin; Background.xCoordBg2 = com.glover.spellingbutterfly.Constants.SCREEN_WIDTH;
        }
    }
}

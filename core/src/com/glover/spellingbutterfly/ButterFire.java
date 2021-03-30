package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ButterFire {

    public static Texture butterFire;
    public static Array<Rectangle> butterFired;
    public static final int BUTTERFIRE_HEIGHT = 10;
    public static final int BUTTERFIRE_WIDTH = 32;


    public static void load() {
        butterFire = new Texture(Gdx.files.internal("images/butterfire.png"));
        butterFired = new Array<Rectangle>();

//        FireButterBullet(-10);



    }

    public static void FireButterBullet (int yCord) {
        Rectangle butterFireTmp = new Rectangle();
        butterFireTmp.x = 220;
        butterFireTmp.y = yCord;
        butterFireTmp.width = BUTTERFIRE_WIDTH;
        butterFireTmp.height = BUTTERFIRE_HEIGHT;
        butterFired.add(butterFireTmp);
    }


}


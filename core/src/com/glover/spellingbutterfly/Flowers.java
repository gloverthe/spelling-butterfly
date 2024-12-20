package com.glover.spellingbutterfly;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.org.apache.bcel.internal.Const;

import java.util.ArrayList;

public class Flowers {
    public static Texture pinkFlower_1_Sprite;
    public static Texture pinkFlower_2_Sprite;
    public static Texture flowerToDraw;
    public static Sound flowerPop;
    public static Array<Rectangle> flowers;
    public static ArrayList<Texture> flowerSprites;
    public static long lastFlowerTime;
    public static int pinkFlower_1_Height = 146;
    public static int pinkFlower_1_Width = 110;
    public static int pinkFlower_2_Height = 100;
    public static int pinkFlower_2_Width = 100;

    public final static int FLOWER_POINTS = 2;

    public static void load() {


        flowerPop = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.wav"));

//		create the flowers
        pinkFlower_1_Sprite	= new Texture(Gdx.files.internal("pinkFlower_1.png"));
        pinkFlower_1_Sprite.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pinkFlower_2_Sprite	= new Texture(Gdx.files.internal("pinkFlower_2.png"));
        pinkFlower_2_Sprite.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        flowers = new Array<Rectangle>();
        flowerSprites = new ArrayList<Texture>();
        spawnFlower();

    }

    public static void spawnFlower() {
        boolean whichFlower = MathUtils.randomBoolean();
//        System.out.println(whichFlower);
        if (whichFlower) {

            Rectangle pinkFlower_1 = new Rectangle();
            pinkFlower_1.x = Constants.SCREEN_WIDTH;
            pinkFlower_1.y = MathUtils.random(0, Constants.SCREEN_HEIGHT - pinkFlower_1_Height);
            pinkFlower_1.width = pinkFlower_1_Width;
            pinkFlower_1.height = pinkFlower_1_Height;
            flowers.add(pinkFlower_1);
            flowerSprites.add(pinkFlower_1_Sprite);
//            flowerToDraw = pinkFlower_1_Sprite;

        }
        if (!whichFlower) {
            Rectangle pinkFlower_1 = new Rectangle();
            pinkFlower_1.x = Constants.SCREEN_WIDTH;
            pinkFlower_1.y = MathUtils.random(0, Constants.SCREEN_HEIGHT - pinkFlower_2_Height);
            pinkFlower_1.width = pinkFlower_2_Width;
            pinkFlower_1.height = pinkFlower_2_Height;
            flowers.add(pinkFlower_1);
            flowerSprites.add(pinkFlower_2_Sprite);
//            flowerSprites.add("pinkFlower_2_Sprite");

        }
        lastFlowerTime = TimeUtils.nanoTime();
//        for(Rectangle pinkFlower_1: flowers) {
//            System.out.println(pinkFlower_1);
//        }
//        for(Texture flowerT : flowerSprites) {
//            System.out.println(flowerT);
//        }
    }

}

package com.glover.spellingbutterfly;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Flowers {
    public static Texture pinkFlower_1_Sprite;
    public static Texture pinkFlower_2_Sprite;
    public static Texture flowerToDraw;
    public static Sound flowerPop;
    public static Array<Rectangle> flowers;
    public static long lastFlowerTime;
    public static int pinkFlower_1_Height = 146;
    public static int pinkFlower_1_Width = 110;
    public static int pinkFlower_2_Height = 100;
    public static int pinkFlower_2_Width = 100;

    public final static int FLOWER_POINTS = 2;

    public static void load() {


        flowerPop = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));

//		create the flowers
        pinkFlower_1_Sprite	= new Texture(Gdx.files.internal("pinkFlower_2.png"));
        pinkFlower_2_Sprite	= new Texture(Gdx.files.internal("pinkFlower_1.png"));

        flowers = new Array<Rectangle>();
        spawnFlower();

    }

    public static void spawnFlower() {
        boolean whichFlower = MathUtils.randomBoolean();
        if (whichFlower == true) {

            Rectangle pinkFlower_1 = new Rectangle();
            pinkFlower_1.x = Screen.SCREEN_WIDTH;
            pinkFlower_1.y = MathUtils.random(0, Screen.SCREEN_HEIGHT - pinkFlower_1_Height);
            pinkFlower_1.width = pinkFlower_1_Width;
            pinkFlower_1.height = pinkFlower_1_Height;
            flowers.add(pinkFlower_1);
            flowerToDraw = pinkFlower_1_Sprite;
            lastFlowerTime = TimeUtils.nanoTime();

        }
        if (whichFlower == false) {
            Rectangle pinkFlower_1 = new Rectangle();
            pinkFlower_1.x = Screen.SCREEN_WIDTH;
            pinkFlower_1.y = MathUtils.random(0, Screen.SCREEN_HEIGHT - pinkFlower_2_Height);
            pinkFlower_1.width = pinkFlower_2_Width;
            pinkFlower_1.height = pinkFlower_2_Height;
            flowers.add(pinkFlower_1);
            flowerToDraw = pinkFlower_2_Sprite;
            lastFlowerTime = TimeUtils.nanoTime();
        }
    }

}

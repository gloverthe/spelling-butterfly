package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Butterfly {
    public static Texture butterflySprite_1;
    public static  Texture butterflySprite_2;
    public static Texture butterflySprite_3;
    public static  Sound wingFlap;
    public static  Rectangle butterfly;
    public static Animation<TextureRegion> butterflySprite;
    public static float butterflyStateTime = 0;
    public static final int BUTTERFLY_FALL_SPEED = -110;


    public static void load() {

        //		create butterfly
        butterflySprite_1 = new Texture(Gdx.files.internal("Butterfly_1.png"));
        butterflySprite_2 = new Texture(Gdx.files.internal("Butterfly_3.png"));
        butterflySprite_3 = new Texture(Gdx.files.internal("Butterfly_2.png"));

        butterflySprite_1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        butterflySprite = 	new Animation<TextureRegion>(0.05f,
                new TextureRegion(butterflySprite_1),
                new TextureRegion(butterflySprite_2),
                new TextureRegion(butterflySprite_3),
                new TextureRegion(butterflySprite_1));
        butterflySprite.setPlayMode(Animation.PlayMode.LOOP);


        wingFlap = Gdx.audio.newSound(Gdx.files.internal("butterflyFlapPCM.wav"));

        butterfly = new Rectangle();
        butterfly.x = 20;
        butterfly.y = (1280 / 2) - (butterfly.height / 2);
        butterfly.width = 205;
        butterfly.height = 190;
    }



}

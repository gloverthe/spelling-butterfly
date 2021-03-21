package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Butterfly {



    public static void create() {

        Texture butterflySprite;
        Sound wingFlap;
        Rectangle butterfly;

        //		create butterfly
        butterflySprite = new Texture(Gdx.files.internal("butterfly-removebg.png"));
//		wingFlap = Gdx.audio.newSound(Gdx.files.internal("butterflyFlap.wav"));
        butterfly = new Rectangle();
        butterfly.x = 20;
        butterfly.y = (1200 / 2) - (190 / 2);
        butterfly.width = 217;
        butterfly.height = 190;

    }



}

package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Boxes {

    //	Background images
    public static Texture blueTextBox;
    public static Rectangle blueTextBoxDeclare;

    public static Texture blueTextBox_600x150;
    public static Rectangle blueTextBoxDeclare_600x150;
    public static Array<Rectangle> guessBoxes;

   public static Texture guessBox;
//   public static Rectangle guessBoxOneDelcare;

    public static void load() {
        blueTextBox = new Texture(Gdx.files.internal("blueTextBox_1200x150.png"));
        blueTextBoxDeclare = new Rectangle();


        blueTextBoxDeclare.x = 960 -600;
        blueTextBoxDeclare.y = 960 -60 ;
        blueTextBoxDeclare.width = 1200;
        blueTextBoxDeclare.height = 150;


        blueTextBox_600x150 = new Texture(Gdx.files.internal("blueTextBox_600x150.png"));
        blueTextBoxDeclare_600x150 = new Rectangle();


        blueTextBoxDeclare_600x150.x = 960 -300;
        blueTextBoxDeclare_600x150.y = 640 -75 ;
        blueTextBoxDeclare_600x150.width = 600;
        blueTextBoxDeclare_600x150.height = 150;

        guessBox = new Texture(Gdx.files.internal("blueTextBox_300x150.png"));

        guessBoxes= new Array<Rectangle>();
        Boxes.blueTextBox_300x150(480-150, 320);
        Boxes.blueTextBox_300x150(960-150, 320);
        Boxes.blueTextBox_300x150(1440-150, 320);
//        guessBoxOneDelcare = new Rectangle();
//
//
//        guessBoxOneDelcare.x = xPos;
//        guessBoxOneDelcare.y = yPos ;
//        guessBoxOneDelcare.width = 300;
//        guessBoxOneDelcare.height = 150;





    }

    public static void blueTextBox_300x150(int xPos, int yPos ) {
//        Texture blueTextBox_300x150;
//        Rectangle blueTextBoxDeclare_300x150;
        Rectangle blueTextBoxDeclare_300x150 = new Rectangle();
        blueTextBoxDeclare_300x150.x = xPos;
        blueTextBoxDeclare_300x150.y = yPos ;
        blueTextBoxDeclare_300x150.width = 300;
        blueTextBoxDeclare_300x150.height = 150;
        guessBoxes.add(blueTextBoxDeclare_300x150);

    }
}


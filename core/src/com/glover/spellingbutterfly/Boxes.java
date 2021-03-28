package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Boxes {

    //	Background images
    public static Texture blueTextBox;
    public static Rectangle blueTextBoxDeclare;
    public static Sound rightAnswer;
    public static Sound wrongAnswer;
    public static Texture blueTextBox_600x150;
    public static Rectangle blueTextBoxDeclare_600x150;
    public static Array<Rectangle> guessBoxes;
    public static Texture guessBox;
    public static Texture guessBoxPressed;
//   public static Rectangle guessBoxOneDelcare;

    public static void load() {

        //box press sounds
        rightAnswer = Gdx.audio.newSound(Gdx.files.internal("sounds/yay.wav"));
        wrongAnswer = Gdx.audio.newSound(Gdx.files.internal("sounds/wrongAnswer.wav"));

        blueTextBox = new Texture(Gdx.files.internal("images/glossybluebutton_1300x200.png"));
        blueTextBox.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        blueTextBoxDeclare = new Rectangle();
        blueTextBoxDeclare.width = 1300;
        blueTextBoxDeclare.height = 200;

        blueTextBoxDeclare.x = (Constants.SCREEN_WIDTH / 2) - (blueTextBoxDeclare.width / 2);
        blueTextBoxDeclare.y = (Constants.SCREEN_HEIGHT - (Constants.SCREEN_HEIGHT / 4))- (blueTextBoxDeclare.height/2 );

//


        blueTextBox_600x150 = new Texture(Gdx.files.internal("images/question_800x200.png"));
        blueTextBox_600x150.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        blueTextBoxDeclare_600x150 = new Rectangle();

        blueTextBoxDeclare_600x150.width = 800;
        blueTextBoxDeclare_600x150.height = 200;
        blueTextBoxDeclare_600x150.x = (Constants.SCREEN_WIDTH / 2) - (blueTextBoxDeclare_600x150.width / 2);
        blueTextBoxDeclare_600x150.y = (Constants.SCREEN_HEIGHT - (Constants.SCREEN_HEIGHT / 2))- (blueTextBoxDeclare_600x150.height / 2) ;
//        blueTextBoxDeclare_600x150.x = 960 -400;
//        blueTextBoxDeclare_600x150.y = 640 -75 ;


        guessBox = new Texture(Gdx.files.internal("images/glossybluebutton_300x200.png"));
        guessBox.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        guessBoxPressed = new Texture(Gdx.files.internal("images/glossybluebuttonpressed_300x200.png"));
        guessBoxPressed.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        guessBoxes= new Array<Rectangle>();
        Boxes.blueTextBox_300x150((Constants.SCREEN_WIDTH/4)-150, (Constants.SCREEN_HEIGHT - ((Constants.SCREEN_HEIGHT / 4)*3)) - 100);
        Boxes.blueTextBox_300x150((Constants.SCREEN_WIDTH/2)-150, (Constants.SCREEN_HEIGHT - ((Constants.SCREEN_HEIGHT / 4)*3)) - 100);
        Boxes.blueTextBox_300x150(((Constants.SCREEN_WIDTH / 4)*3) -150, (Constants.SCREEN_HEIGHT - ((Constants.SCREEN_HEIGHT / 4)*3)) - 100);
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
        blueTextBoxDeclare_300x150.height = 200;
        guessBoxes.add(blueTextBoxDeclare_300x150);

    }
}


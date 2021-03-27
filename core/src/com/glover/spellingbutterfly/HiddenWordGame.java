package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import javax.swing.*;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HiddenWordGame implements Screen {

    final SpellingButterfly game;

//    public static Rectangle blueTextBoxDeclare;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ArrayList<String> currentWordListArray = new ArrayList<>();
    private int wordListArrayLength;
    public int questionY = 0;
    public int questionX = 0;
//    private ShapeRenderer shapeRenderer;
    public String questionString;
    public String guessOne;
    public String guessTwo;
    public String guessThree;
    public int answerBox;
    public Object guessBoxOne;

    public static ArrayList<String> wordListArray = new ArrayList<>();
    public static ArrayList<String> questionAndAnswer = new ArrayList<>();



    public HiddenWordGame (final SpellingButterfly playGame) {
        game = playGame;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,  com.glover.spellingbutterfly.Constants.SCREEN_WIDTH , com.glover.spellingbutterfly.Constants.SCREEN_HEIGHT);

        wordListArray = ReadWords.wordListArray("level_1");
        wordListArrayLength = currentWordListArray.size();

        Boxes.load();


//         Boxes.blueTextBox_300x150(100,100);



//        wordListArray= readWords();
//        wordListArray.forEach(System.out::println);

//        hideWord(wordListArray).forEach(System.out::println);
        questionAndAnswer = hideWord(wordListArray);

//        questionAndAnswer.forEach(System.out::println);

        Integer[] intArray = { 1, 2 ,3 };
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);

        System.out.println("Last word : " + wordListArray.get(wordListArrayLength));
        System.out.println(Arrays.toString(intArray));
        System.out.println("Can you find the word: " + questionAndAnswer.get(0));
        System.out.println("Word 1: " + questionAndAnswer.get(intArray[0]));
        System.out.println("Word 2: " + questionAndAnswer.get(intArray[1]));
        System.out.println("Word 3: " + questionAndAnswer.get(intArray[2]));


        questionString = questionAndAnswer.get(0);
        guessOne = questionAndAnswer.get(intArray[0]);
        guessTwo = questionAndAnswer.get(intArray[1]);
        guessThree = questionAndAnswer.get(intArray[2]);

        if (intArray[0] == 1) answerBox = 1;
        if (intArray[1] == 1) answerBox = 2;
        if (intArray[2] == 1) answerBox = 3;

        System.out.println( "The answer is in box : " + answerBox);



    }

    public static ArrayList<String> hideWord(ArrayList<String> currentWordListArray) {

        int currentWordListArrayLength = currentWordListArray.size();
        int randomWordPos = (int) ((Math.random() * (currentWordListArrayLength)) + 0);


        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("(?!^)");
        int questionLength = 10;
        String[] randomWord = currentWordListArray.get(randomWordPos).split("(?!^)");
        String randomWordString = currentWordListArray.get(randomWordPos);
        currentWordListArray.remove(randomWordPos);
        currentWordListArrayLength -- ;
        int randomWordLength = randomWord.length;
        int randomWordStartPos = (int) ((Math.random() * ((questionLength - randomWordLength))) + 0);
//        System.out.println("Word Length " + randomWordLength);
//        System.out.println(("Word Start pos " + randomWordStartPos));

        int i = 0;
        int j = 0;
        StringBuilder hiddenWord = new StringBuilder("");
        while (i < 10) {
            if (i < randomWordStartPos || i > randomWordStartPos + (randomWordLength-1)) {
                int z = (int) ((Math.random() * (25 - 0)) + 0);
                hiddenWord.append(alphabet[z]);
            } else {
                hiddenWord.append(randomWord[j]);
                j++;
            }
            i++;
//            System.out.println(hiddenWord.toString());
        }
//        String hiddenWordOut = hiddenWord.toString();

        int guessOneInt = (int) ((Math.random() * (currentWordListArrayLength)) + 0);
        String guessOne = currentWordListArray.get(guessOneInt);
        currentWordListArray.remove(guessOneInt);
        currentWordListArrayLength --;
        int guessTwoInt = (int) ((Math.random() * (currentWordListArrayLength)) + 0);
        String guessTwo = currentWordListArray.get(guessTwoInt);
        currentWordListArray.remove(guessTwoInt);
//        currentWordListArrayLength --;

        ArrayList<String> questionAndAnswer = new ArrayList<>();
        questionAndAnswer.add(hiddenWord.toString());
        questionAndAnswer.add(randomWordString);
        questionAndAnswer.add(guessOne);
        questionAndAnswer.add(guessTwo);
        return questionAndAnswer;
    }



        @Override
        public void render(float delta) {

//        hideWordsToStrings();
//        String randomWord;


            game.batch.begin();


            game.batch.draw(Boxes.blueTextBox, Boxes.blueTextBoxDeclare.x, Boxes.blueTextBoxDeclare.y);
            game.batch.draw(Boxes.blueTextBox_600x150, Boxes.blueTextBoxDeclare_600x150.x, Boxes.blueTextBoxDeclare_600x150.y);
//            game.batch.draw(Boxes.guessBox, Boxes.blueTextBox_300x150(300, 150));
            for (Rectangle box : Boxes.guessBoxes) {
                game.batch.draw(Boxes.guessBox, box.x, box.y);
            }
//            game.batch.draw(Boxes.guessBox, Boxes.guessBoxes.get(0).x,

            game.font.draw(game.batch, "Which word can you see? ", (960 - 580) + 5, 960 + 30);
            game.font.draw(game.batch, questionString, 960 - 215, 665);

            game.font.draw(game.batch, guessOne, 480 - 60, 410);
            game.font.draw(game.batch, guessTwo, 960 - 60, 410);
            game.font.draw(game.batch, guessThree, 1440 - 60, 410);
//            System.out.println(hideWord()[0]);
//            System.out.println(guessOne);
            game.batch.end();

            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                System.out.println("X coord = " + Gdx.input.getX() + ", Y coord = " + Gdx.input.getY());
                camera.unproject(touchPos);
                if (answerBox == 1 && Boxes.guessBoxes.get(0).contains(touchPos.x, touchPos.y)) {
                        game.setScreen(game.mainGame);
                        dispose();
                    }
                if (answerBox == 2 && Boxes.guessBoxes.get(1).contains(touchPos.x, touchPos.y)) {
                        game.setScreen(game.mainGame);
                        dispose();
                    }
                if (answerBox == 3 && Boxes.guessBoxes.get(2).contains(touchPos.x, touchPos.y)) {
                        game.setScreen(game.mainGame);
                        dispose();
                    }
                }
            }



    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose () {


    }

}



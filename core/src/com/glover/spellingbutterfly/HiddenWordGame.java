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
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.objects.XStringForChars;

import javax.swing.*;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HiddenWordGame implements Screen {

    private static final int ANSWER_POINTS = 25;
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
    private String level;


    public HiddenWordGame (final SpellingButterfly playGame) {


        game = playGame;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,  Constants.SCREEN_WIDTH , Constants.SCREEN_HEIGHT);

       level = "level_1";
        if(SpellingButterfly.totalScore > 100) level = "level_2";
        if(SpellingButterfly.totalScore > 200) level = "level_3";
        if(SpellingButterfly.totalScore > 300) level = "level_4";
        if(SpellingButterfly.totalScore > 400) level = "level_5";
        if(SpellingButterfly.totalScore > 500) level = "level_6";
        if(SpellingButterfly.totalScore > 600) level = "level_7";


        wordListArray = ReadWords.wordListArray(level);
        wordListArrayLength = currentWordListArray.size();

        Boxes.load();


        questionAndAnswer = hideWord(wordListArray);

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

        int guessOneInt = (int) ((Math.random() * (currentWordListArrayLength-1)) + 0);
        System.out.println("Guess 1 int is : " + guessOneInt);
        String guessOne = currentWordListArray.get(guessOneInt);
        currentWordListArray.remove(guessOneInt);
        currentWordListArrayLength --;
        int guessTwoInt = (int) ((Math.random() * (currentWordListArrayLength-1)) + 0);
        System.out.println("Guess 2 int is : " + guessTwoInt);
        String guessTwo = currentWordListArray.get(guessTwoInt);
        currentWordListArray.remove(guessTwoInt);
//        currentWordListArrayLength --;

        ArrayList<String> questionAndAnswer = new ArrayList<>();
        questionAndAnswer.add((hiddenWord.toString()).trim());
        questionAndAnswer.add(randomWordString.trim());
        questionAndAnswer.add(guessOne.trim());
        questionAndAnswer.add(guessTwo.trim());
        return questionAndAnswer;
    }

    public void correctAnswer() {
        Boxes.rightAnswer.play();
        game.setScreen(game.mainGame);
        SpellingButterfly.totalScore += HiddenWordGame.ANSWER_POINTS;
        dispose();

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
            game.font.draw(game.batch, questionString, 960 - 225, 665);

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
                if (Boxes.guessBoxes.get(0).contains(touchPos.x, touchPos.y)) {
                    if (answerBox == 1) {
                        Boxes.rightAnswer.play();
                        SpellingButterfly.totalScore += ANSWER_POINTS;
                        game.setScreen(game.mainGame);
                        dispose();
                        }
                    else Boxes.wrongAnswer.play();
                    }
                if (Boxes.guessBoxes.get(1).contains(touchPos.x, touchPos.y)) {
                    if (answerBox == 2) {
                        Boxes.rightAnswer.play();
                        SpellingButterfly.totalScore += ANSWER_POINTS;
                        game.setScreen(game.mainGame);
                        dispose();
                    }
                    else Boxes.wrongAnswer.play();
                }
                if (Boxes.guessBoxes.get(2).contains(touchPos.x, touchPos.y)) {
                    if (answerBox == 3) {
                        Boxes.rightAnswer.play();
                        SpellingButterfly.totalScore += ANSWER_POINTS;
                        game.setScreen(game.mainGame);
                        dispose();
                    }
                    else Boxes.wrongAnswer.play();
                }
//                else if (answerBox == 2 && Boxes.guessBoxes.get(1).contains(touchPos.x, touchPos.y)) {
//                        Boxes.rightAnswer.play();
//                        game.setScreen(game.mainGame);
//                        dispose();
//                    }
//                else if (answerBox == 3 && Boxes.guessBoxes.get(2).contains(touchPos.x, touchPos.y)) {
//                        Boxes.rightAnswer.play();
//                        game.setScreen(game.mainGame);
//                        dispose();
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



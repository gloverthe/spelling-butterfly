package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HiddenWordGame implements Screen {

    final SpellingButterfly game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ArrayList<String> currentWordListArray = new ArrayList<>();
    private int currentWordListArrayLength;
    public int questionY = 0;
    public int questionX = 0;

    public String questionString;
    public String guessOne;
    public String guessTwo;
    public String guessThree;

    public static ArrayList<String> wordListArray = new ArrayList<>();
    public static ArrayList<String> questionAndAnswer = new ArrayList<>();



    public HiddenWordGame (final SpellingButterfly playGame) {
        game = playGame;

        wordListArray = ReadWords.wordListArray("Level_1");
//        wordListArrayLength = currentWordListArray.size();

//        wordListArray= readWords();
//        wordListArray.forEach(System.out::println);

//        hideWord(wordListArray).forEach(System.out::println);
        questionAndAnswer = hideWord(wordListArray);

//        questionAndAnswer.forEach(System.out::println);

        Integer[] intArray = { 1, 2 ,3 };
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);


        System.out.println(Arrays.toString(intArray));
        System.out.println("Can you find the word: " + questionAndAnswer.get(0));
        System.out.println("Word 1: " + questionAndAnswer.get(intArray[0]));
        System.out.println("Word 2: " + questionAndAnswer.get(intArray[1]));
        System.out.println("Word 3: " + questionAndAnswer.get(intArray[2]));

        questionString = questionAndAnswer.get(0);
        guessOne = questionAndAnswer.get(intArray[0]);
        guessTwo = questionAndAnswer.get(intArray[1]);
        guessThree = questionAndAnswer.get(intArray[2]);


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


//    public void hideWordsToStrings() {
////      Convert the results of the hideWord method to strings to render
//        for (int i = 0; i < hideWord().length; i++) {
//            if (i == 0) {
//                questionString = hideWord()[i];
//            }
//            if (i == 1) {
//                guessOne = hideWord()[i];
//            }
//            if (i == 2) {
//                guessTwo = hideWord()[i];
//            }
//            if (i == 3) {
//                guessThree = hideWord()[i];
//            }
//        }
//
//
//    }


        @Override
        public void render(float delta) {

//        hideWordsToStrings();


//        String randomWord;

//            ScreenUtils.clear(0, 0, 0.2f, 1);

//            camera.update();
//            game.batch.setProjectionMatrix(camera.combined);
//            System.out.println("Spell a word");



            game.batch.begin();
            game.font.draw(game.batch, "Find the word: ", 100, 1100);
            game.font.draw(game.batch, questionString , 100, 1000);
            game.font.draw(game.batch, guessOne + "   " + guessTwo + "   " + guessThree, 100, 800);
//            System.out.println(hideWord()[0]);
//            System.out.println(guessOne);
            game.batch.end();

            if (Gdx.input.isTouched()) {
//                game.setScreen(MainGame(game));
                dispose();
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



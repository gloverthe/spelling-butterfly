package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class HiddenWordGame implements Screen {

    final SpellingButterfly game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ArrayList<String> currentWordListArray = new ArrayList<>();
    private int currentWordListArrayLength;
    public int questionY = 0;
    public int questionX = 0;

    public HiddenWordGame (final SpellingButterfly playGame) {
        game = playGame;

        currentWordListArray = ReadWords.wordListArray("Level_1");
        currentWordListArrayLength = currentWordListArray.size();

    }

    public String[] hideWord() {

        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("(?!^)");
        int questionLength = 10;
        int randomWordPos = MathUtils.random(0, currentWordListArrayLength);
        String[] randomWord = currentWordListArray.get(randomWordPos).split("(?!^)");
        String randomWordString = currentWordListArray.get(randomWordPos);
        int randomWordLength = randomWord.length;
        int randomWordStartPos = MathUtils.random(0, questionLength - randomWordLength);
        int i = 0;
        int j = 0;
        StringBuilder hiddenWord = null;
        while (i < 10) {
            if(i < randomWordStartPos && i > randomWordStartPos + randomWordLength) {
                hiddenWord.append(alphabet[MathUtils.random(0, 25)]);
            }
            else {
                hiddenWord.append(randomWord[j]);
                j ++;
            }
            i ++;
        }
//        String hiddenWordOur = toString(hiddenWord);
        String[] questionAndAnswer = {hiddenWord.toString(),
                            currentWordListArray.get(MathUtils.random(0, currentWordListArrayLength)),
                            randomWordString,
                            currentWordListArray.get(MathUtils.random(0, currentWordListArrayLength))
        };


//        0 1 2 3 4 5 6 7 8 9
//          r a t

//        System.out.println("The word is "+ randomWord);
//        questionAndAnswer[0] =


        return questionAndAnswer;
    }



        @Override
        public void render(float delta) {
        String randomWord;

//            ScreenUtils.clear(0, 0, 0.2f, 1);

//            camera.update();
//            game.batch.setProjectionMatrix(camera.combined);
//            System.out.println("Spell a word");



            game.batch.begin();
            game.font.draw(game.batch, "Find the word: ", 100, 1100);
            game.font.draw(game.batch, hideWord()[0], 100, 1000);
            game.font.draw(game.batch, hideWord()[1] + "   " + hideWord()[2] + "   " + hideWord()[3], 100, 800);
            game.batch.end();

//            if (Gdx.input.isTouched()) {
//                game.setScreen(new GameScreen(game));
//                dispose();
//            }
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



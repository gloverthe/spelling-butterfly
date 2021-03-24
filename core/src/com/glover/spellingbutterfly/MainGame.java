package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;



import java.util.ArrayList;
import java.util.Iterator;

public class MainGame implements Screen {

    final SpellingButterfly game;

    //	Other
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private int flowerCounter;
    private int totalScore;
//    public BitmapFont font;
    private long flapTime;

    public MainGame(final SpellingButterfly playGame) {
        this.game =playGame;

        Background.load();
        Butterfly.load();
        Flowers.load();


//		create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,  com.glover.spellingbutterfly.Constants.SCREEN_WIDTH , com.glover.spellingbutterfly.Constants.SCREEN_HEIGHT);


    }
    @Override
    public void render(float delta) {

        float deltaTime = Gdx.graphics.getDeltaTime();
        Butterfly.butterflyStateTime += deltaTime;






//		Render Background
        Background.xCoordBg1 += Background.BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
        Background.xCoordBg2 = Background.xCoordBg1 + Background.xMax;  // We move the background, not the camera
        if (Background.xCoordBg1 <= - com.glover.spellingbutterfly.Constants.SCREEN_WIDTH ) {
            Background.xCoordBg1 = Background.xMin; Background.xCoordBg2 = com.glover.spellingbutterfly.Constants.SCREEN_WIDTH;
        }


        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(Background.flowerBackground1, Background.xCoordBg1, 0);
        game.batch.draw(Background.flowerBackground2, Background.xCoordBg2, 0);
        game.font.draw(game.batch, "Score: " + totalScore, 1500, 70);
        game.batch.draw(Butterfly.butterflySprite.getKeyFrame(Butterfly.butterflyStateTime), Butterfly.butterfly.x, Butterfly.butterfly.y);

        for(Rectangle pinkFlower_1: Flowers.flowers) {
            game.batch.draw(Flowers.flowerToDraw, pinkFlower_1.x, pinkFlower_1.y);
        }

//		DEBUG LINEs
//		System.out.println("X BG1 = " + xCoordBg1 + ", X BG2 = " + xCoordBg2);
//		System.out.println("Which flower is " + whichFlower + " and flowerToDraw is : " + flowerToDraw);
//		System.out.println(("Total Score: " + totalScore));
        game.batch.end();

        if(Gdx.input.isTouched()) {
            //need to fix input so touch does not send it crazy
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			butterfly.y = touchPos.y - (190 / 2);

            Butterfly.butterfly.y += 2000 * Gdx.graphics.getDeltaTime();
            if (TimeUtils.nanoTime() - flapTime> 100000000) {
                Butterfly.wingFlap.play();
                flapTime = TimeUtils.nanoTime();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) Butterfly.butterfly.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) Butterfly.butterfly.y -= 200 * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Butterfly.butterfly.y += 2000 * Gdx.graphics.getDeltaTime();
            Butterfly.wingFlap.play();
        }

        Butterfly.butterfly.y += -150 * Gdx.graphics.getDeltaTime();
        if(Butterfly.butterfly.y < 0) Butterfly.butterfly.y = 0;
        if(Butterfly.butterfly.y > com.glover.spellingbutterfly.Constants.SCREEN_HEIGHT - 190) Butterfly.butterfly.y =  com.glover.spellingbutterfly.Constants.SCREEN_HEIGHT - 190;


//		flowers
        if(TimeUtils.nanoTime() - Flowers.lastFlowerTime > 1000000000) Flowers.spawnFlower();

        for (Iterator<Rectangle> iter = Flowers.flowers.iterator(); iter.hasNext(); ) {
            Rectangle pinkFlower_1 = iter.next();
            pinkFlower_1.x -= 200 * Gdx.graphics.getDeltaTime();
            if(pinkFlower_1.x  < ( - Flowers.pinkFlower_1_Width)) {
                iter.remove();
                totalScore--;

            }

            if(pinkFlower_1.overlaps(Butterfly.butterfly)) {
                Flowers.flowerPop.play();
                flowerCounter ++;
                totalScore += Flowers.FLOWER_POINTS;
                if(flowerCounter % 1 == 0) {
                    game.setScreen(new HiddenWordGame(game));
//                    System.out.println("Spell a word");
//                    randomWord = currentWordListArray.get(MathUtils.random(0, currentWordListArrayLength));
//                    System.out.println("The word is "+ randomWord);

                }
                iter.remove();
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
        Background.flowerBackground1.dispose();
        Background.flowerBackground2.dispose();
        Flowers.pinkFlower_1_Sprite.dispose();
        Flowers.pinkFlower_2_Sprite.dispose();
        Butterfly.butterflySprite_1.dispose();
        Butterfly.butterflySprite_2.dispose();
        Butterfly.butterflySprite_3.dispose();

    }

}

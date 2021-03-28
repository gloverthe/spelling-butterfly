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
import java.lang.Math;
import java.util.Iterator;


public class MainGame implements Screen {

    final SpellingButterfly game;

    //	Other
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private int flowerCounter;

//    public BitmapFont font;
    private long flapTime;

    public MainGame(final SpellingButterfly playGame) {
        this.game =playGame;

        Background.load();
        Butterfly.load();
        Flowers.load();
        ButterFire.load();


//		create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,  com.glover.spellingbutterfly.Constants.SCREEN_WIDTH , com.glover.spellingbutterfly.Constants.SCREEN_HEIGHT);



    }
    @Override
    public void render(float delta) {

        float deltaTime = Gdx.graphics.getDeltaTime();
        Butterfly.butterflyStateTime += deltaTime;


//		Render Background
        Background.renderBackground();


        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(Background.flowerBackground1, Background.xCoordBg1, 0);
        game.batch.draw(Background.flowerBackground2, Background.xCoordBg2, 0);
        game.font.draw(game.batch, "Score: " + SpellingButterfly.totalScore, 1450, 70);
//        game.batch.draw(Butterfly.butterflySprite.getKeyFrame(Butterfly.butterflyStateTime), Butterfly.butterfly.x, Butterfly.butterfly.y);
        game.batch.draw(Butterfly.butterflySprite.getKeyFrame(Butterfly.butterflyStateTime), Butterfly.butterfly.x, Butterfly.butterfly.y);

//        (ButterFire.butterFired.size > 0) {
            for (Rectangle bullets : ButterFire.butterFired) {
                game.batch.draw(ButterFire.butterFire, bullets.x, bullets.y);
                System.out.println(("Bullets : " + bullets.x + " " + bullets.y));
            }
//        }
//
        for(int i = 0; Flowers.flowerSprites.size() > i; i ++) {
            Flowers.flowers.get(i).y = Flowers.flowers.get(i).y + (-(float)Math.cos(Flowers.flowers.get(i).x / 20) * 10);
//            System.out.println("tried to shower flower");
            game.batch.draw(Flowers.flowerSprites.get(i),  Flowers.flowers.get(i).x, Flowers.flowers.get(i).y);
//            game.batch.draw(Flowers.pinkFlower_1_Sprite,  Flowers.flowers.get(i).x, Flowers.flowers.get(i).y);
//            System.out.println("tried to shower flower, details : " + Flowers.flowerSprites.get(i) );
//            System.out.println("tried to shower flower, details : " + Flowers.flowers.get(i).x  + " " + Flowers.flowers.get(i).y);
        }



        game.batch.end();

        if(Gdx.input.justTouched()) {
            //need to fix input so touch does not send it crazy
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			butterfly.y = touchPos.y - (190 / 2);

            Butterfly.butterfly.y += Butterfly.BUTTERFLY_IMPULSE * Gdx.graphics.getDeltaTime();
            if (TimeUtils.nanoTime() - flapTime> 100000000) {
                Butterfly.wingFlap.play();
                flapTime = TimeUtils.nanoTime();
            }
        }

//        if(Gdx.input.isTouched()) {
//
//            Butterfly.butterflyPosition.set(Butterfly.BUTTERFLY_VELOCITY_Y, Butterfly.BUTTERFLY_IMPULSE);
//            if (TimeUtils.nanoTime() - flapTime> 100000000) {
//                Butterfly.wingFlap.play();
//                flapTime = TimeUtils.nanoTime();
//            }
//        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) ButterFire.FireButterBullet(Math.round(Butterfly.butterfly.y));

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) Butterfly.butterfly.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) Butterfly.butterfly.y -= 200 * Gdx.graphics.getDeltaTime();

//        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//            Butterfly.butterfly.y += 2000 * Gdx.graphics.getDeltaTime();
//            Butterfly.wingFlap.play();
//        }

//        Butterfly.butterflyVelocity.add(Butterfly.gravity);
//        Butterfly.butterflyPosition.mulAdd(Butterfly.butterflyVelocity, deltaTime);

//        Butterfly.butterfly.y += -150 * Gdx.graphics.getDeltaTime();
        if(Butterfly.butterfly.y < 0) Butterfly.butterfly.y = 0;
        if(Butterfly.butterfly.y > Constants.SCREEN_HEIGHT - 190) Butterfly.butterfly.y =  Constants.SCREEN_HEIGHT - 190;

//        if(ButterFire.butterFired.size > 0) {
//            for (Rectangle bullets : ButterFire.butterFired) {
//                bullets.x += 600 * Gdx.graphics.getDeltaTime();
//            }
//        }

        Iterator<Rectangle> iter = ButterFire.butterFired.iterator();
        while (iter.hasNext()) {
            Rectangle bullets = iter.next();
            bullets.x += 600 * Gdx.graphics.getDeltaTime();
            if (bullets.x + 30 > Constants.SCREEN_WIDTH)
                iter.remove();
            for (int irate = 0; Flowers.flowerSprites.size() > irate; irate++) {
                if(bullets.overlaps(Flowers.flowers.get(irate))) {
                    Flowers.flowers.removeIndex(irate);
                    Flowers.flowerSprites.remove(irate);
                    Flowers.flowerPop.play();
                    flowerCounter ++;

                }

            }
//            if (raindrop.overlaps(Assets.bucket)) {
//                dropsGathered++;
//                Assets.dropSound.play();
//                iter.remove();
//            }
        }


//		flowers
        if(TimeUtils.nanoTime() - Flowers.lastFlowerTime > 1000000000) Flowers.spawnFlower();


        for (int irate = 0; Flowers.flowerSprites.size() > irate; irate++) {
            Flowers.flowers.get(irate).x -= 400 * Gdx.graphics.getDeltaTime();
            if(Flowers.flowers.get(irate).x < ( - Flowers.flowerSprites.get(irate).getWidth() )) {
                Flowers.flowers.removeIndex(irate);
                Flowers.flowerSprites.remove(irate);
                SpellingButterfly.totalScore--;
            }
            if(Flowers.flowers.get(irate).overlaps(Butterfly.butterfly)) {
                Flowers.flowerPop.play();
                flowerCounter ++;
                SpellingButterfly.totalScore += Flowers.FLOWER_POINTS;
                if(flowerCounter % Constants.FLOWERS_FOR_PUZZLE == 0) {
                    game.setScreen(new HiddenWordGame(game));
                }
                Flowers.flowers.removeIndex(irate);
                Flowers.flowerSprites.remove(irate);
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
        this.show();
        this.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose () {


    }

}

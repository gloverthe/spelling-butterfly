package com.glover.spellingbutterfly;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class EnemyFire {

    private static ArrayList<Float> playerXPos;
    private static ArrayList<Float> playerYPos;
    private static ArrayList<Float> enemyXPos;
    private static ArrayList<Float> enemyYPos;
    private static ArrayList<Float> enemyBulletRatio;

    public static Array<Rectangle> enemyBullets;
    public static Long enemyBulletTime;



    public static void load() {
        playerXPos = new ArrayList<Float>();
        playerYPos = new ArrayList<Float>();
        enemyXPos = new ArrayList<Float>();
        enemyYPos = new ArrayList<Float>();
        enemyBulletRatio = new ArrayList<Float>();
        enemyBullets = new Array<Rectangle>();
        enemyBulletTime = TimeUtils.nanoTime();

    }

    public static void addCoords (float playerX, float playerY, float enemyX, float enemyY) {
        playerXPos.add(playerX);
        playerYPos.add(playerY);
        enemyXPos.add(enemyX);
        enemyYPos.add(enemyY);
        enemyBulletRatio.add((enemyY - playerY) / (enemyX - playerX));


    }

    public static void removeCoords (int coordsIndex) {
        playerXPos.remove(coordsIndex);
        playerYPos.remove(coordsIndex);
        enemyXPos.remove(coordsIndex);
        enemyYPos.remove(coordsIndex);
        enemyBulletRatio.remove(coordsIndex);
    }

    public static Float[] getCoords(int coordsIndex) {
        Float[] firePositions = new Float[5];
        firePositions[0] = playerXPos.get(coordsIndex);
        firePositions[1] = playerYPos.get(coordsIndex);
        firePositions[2] = enemyXPos.get(coordsIndex);
        firePositions[3] = enemyYPos.get(coordsIndex);
        firePositions[4] = enemyBulletRatio.get(coordsIndex);
        return firePositions;

    }


    public static void enemyFire(int x, int y) {
                Rectangle enemyBullet = new Rectangle();
                enemyBullet.x = x;
                enemyBullet.y = y;
                enemyBullet.width = ButterFire.BUTTERFIRE_WIDTH;
                enemyBullet.height = ButterFire.BUTTERFIRE_HEIGHT;
                enemyBullets.add(enemyBullet);
                enemyBulletTime = TimeUtils.nanoTime();
                System.out.println("Enemy Bullet Added");
            }


}

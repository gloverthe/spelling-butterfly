package com.glover.spellingbutterfly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.math.MathUtils;

import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWords {
//

    public static ArrayList<String> wordListArray(String filename) {
        ArrayList<String> wordListArray = new ArrayList<>();
        FileHandle file = Gdx.files.internal("WordLists/" + filename + ".txt");
        try {
            BufferedReader br = new BufferedReader(file.reader());
            while (br.ready()) {
                wordListArray.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordListArray;

    }



}
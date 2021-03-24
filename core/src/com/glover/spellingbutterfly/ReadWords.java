package com.glover.spellingbutterfly;

import com.badlogic.gdx.math.MathUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWords {
//

    public static ArrayList<String> wordListArray(String filename) {
        ArrayList<String> wordListArray = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("WordLists/" + filename + ".txt"));
            while (br.ready()) {
                wordListArray.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordListArray;
        //       result.forEach(System.out::println);
//        System.out.println(result.get(4));
//        System.out.println(result.get(5));
    }



}
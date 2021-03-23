package com.glover.spellingbutterfly;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWords {


//    Scanner s = new Scanner(new File(filename));
//    Scanner s = new Scanner(new FileReader(filename));
FileReader f = new FileReader(String "Level_1.txt");


    ArrayList<String> result = new ArrayList<>();
    public String importLevels() {
        FileReader f = new FileReader("Level_1.txt");
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                result.add(sb.toString());
            }

        return result;
         }

}

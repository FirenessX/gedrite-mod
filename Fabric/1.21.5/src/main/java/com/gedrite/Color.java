package com.gedrite;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Color {
    public static void main(String[] args) {
        int i = 0;
        Random ran = new Random();
        List<Integer> api = new ArrayList<Integer>();
        while(i < 3){
            api.add(ran.nextInt(255));
            i += 1;
        }
        String hexColor = String.format("#%02X%02X%02X", api.get(0), api.get(1), api.get(2));
        System.out.print(hexColor);
    }
}

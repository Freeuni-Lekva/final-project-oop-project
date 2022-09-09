package com.quizzetta.HelperClasses;

public class HideText {


    public static String Hide(String input, char symbol) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(symbol);
        }

        return sb.toString();
    }

}

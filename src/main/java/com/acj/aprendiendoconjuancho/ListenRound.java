package com.acj.aprendiendoconjuancho;

import java.util.List;
import java.util.Locale;

public class ListenRound {

    public static int calculateAssertions(String answer, String word) {
        return answer.toLowerCase(Locale.ROOT).equals(word) ? word.length() : 0;
    }
}

package com.acj.aprendiendoconjuancho;

import java.util.*;
import java.util.stream.Collectors;

class SortItemComparator implements Comparator<SortRoundItem> {
    public int compare(SortRoundItem itemA, SortRoundItem itemB) {
        if (itemA.getLetter().getX() == itemB.getLetter().getX())
            return 0;
        else if (itemA.getLetter().getX() > itemB.getLetter().getX())
            return 1;
        else
            return -1;
    }
}

public class SortRound {

    public static int calculateAssertions(List<SortRoundItem> answers, String word) {
        Collections.sort(answers, new SortItemComparator());
        int asserts = 0;
        List<String> goodSortList = new ArrayList<>(Arrays.asList(word.split("")));

        for (int i = 0; i < answers.size(); i++) {
            if(answers.get(i).getLetterNode().getText().equals(goodSortList.get(i))) {
                answers.get(i).setCorrect(true);
                asserts++;
            } else {
                answers.get(i).setCorrect(false);
            }
        }

        return asserts;
    }


}

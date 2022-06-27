package com.acj.aprendiendoconjuancho;

import java.util.List;

public class MatchRound {

    public static int calculateAssertions(List<MatchRoundItem> answers) {
        return answers.stream().map(MatchRoundItem::getOnTarget).filter(ans -> ans).toList().size();
    }
}

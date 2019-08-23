package com.cefetmg.sd.pif.util;

import com.cefetmg.sd.pif.model.Card;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckUtils {

    public static Card win(Card c1, Card c2) {
        return getCardWeight(c1.getCode()) > getCardWeight(c2.getCode()) ? c1 : c2;

    }

    private static int getCardWeight(int code) {
        if (code == 4) { // 4 clovers
            return 100;
        } else if (code == 33) { // 7 hearths
            return 99;
        } else if (code == 40) {// a spades
            return 98;
        } else if (code == 20) { // 7 diamonds
            return 97;
        } else if ((code - 1) % 13 == 2) { // 3
            return 96;
        } else if ((code - 1) % 13 == 1) { // 2
            return 95;
        } else if ((code - 1) % 13 == 0) { // A
            return 94;
        } else if ((code - 1) % 13 == 12) { // K
            return 93;
        } else if ((code - 1) % 13 == 10) { // J
            return 92;
        } else if ((code - 1) % 13 == 11) { // Q
            return 91;
        } else if ((code - 1) % 13 == 6) { // 7
            return 90;
        } else if ((code - 1) % 13 == 5) { // 6
            return 89;
        } else if ((code - 1) % 13 == 4) { // 5
            return 88;
        } else if ((code - 1) % 13 == 3) { // 5
            return 87;
        }
        return 86;
    }

}

package com.cefetmg.sd.pif.util;

public class CardUtils {

    public static String getName(int code){
        // Clovers -> diamonds -> hearths -> spades
        // 1 -> 52
        return getCard(code) + " " + getSuit(code);
    }

    private static String getSuit(int code){
        if(code > 53 || code < 1){
            return null;
        }

        switch ( (code - 1) / 13 ){
            case 0:
                return "C";
            case 1:
                return "D";
            case 2:
                return "H";
            case 3:
                return "S";
            default:
                return null;
        }
    }

    private static String getCard(int code){
        if(code > 53 || code < 1){
            return null;
        }

        switch ((code - 1) % 13){
            case 0:
                return "A";
            case 10:
                return "J";
            case 11:
                return "Q";
            case 12:
                return "K";
            default:
                return String.valueOf(code);
        }
    }

}

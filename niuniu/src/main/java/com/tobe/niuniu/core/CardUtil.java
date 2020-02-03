package com.tobe.niuniu.core;

public class CardUtil {

    public static String cardTypeToString(int cardType) {
        if (cardType == 0) {
            return "♠";
        } else if (cardType == 1) {
            return "♥";
        } else if (cardType == 2) {
            return "♣";
        } else {
            return "♦";
        }
    }

    public static String cardValueToString(int cardValue) {
        if (cardValue == 11) {
            return "J";
        } else if (cardValue == 12) {
            return "Q";
        } else if (cardValue == 13) {
            return "K";
        } else {
            return String.valueOf(cardValue);
        }
    }


}

package com.tobe.niuniu.core;


public class NiuStatus {

    // 0 for no niu and 10 for niu niu
    private int niu;

    private int maxCardType;
    private String maxCardTypeString;

    private int maxCardValue;
    private String maxCardValueString;


    public NiuStatus(int niu, int maxCardType, int maxCardValue) {
        this.niu = niu;
        this.maxCardType = maxCardType;
        this.maxCardTypeString = CardUtil.cardTypeToString(maxCardType);
        this.maxCardValue = maxCardValue;
        this.maxCardValueString = CardUtil.cardValueToString(maxCardValue);
    }

    @Override
    public String toString() {
        String result;

        if (this.niu == 0) {
            result = String.format("No niu, max card %s %s", this.maxCardTypeString, this.maxCardValueString);
        } else {
            result = String.format("Niu %d, max card %s %s", this.niu, this.maxCardTypeString, this.maxCardValueString);
        }

        return result;
    }

}

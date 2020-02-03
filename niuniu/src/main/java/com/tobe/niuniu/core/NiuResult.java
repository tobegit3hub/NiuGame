package com.tobe.niuniu.core;

/**
 * The result of the cards in the game.
 */
public class NiuResult {

    // 0 for no niu and 10 for niu niu
    private int niu = -1;

    private int maxCardType = -1;
    private String maxCardTypeString = null;

    private int maxCardValue = -1;
    private String maxCardValueString = null;

    public NiuResult() {

    }

    public NiuResult(int niu, int maxCardType, int maxCardValue) {
        this.niu = niu;
        this.maxCardType = maxCardType;
        this.maxCardTypeString = CardUtil.cardTypeToString(maxCardType);
        this.maxCardValue = maxCardValue;
        this.maxCardValueString = CardUtil.cardValueToString(maxCardValue);
    }

    public void setNiu(int niu) {
        this.niu = niu;
    }

    public int getNiu() {
        return this.niu;
    }

    public void setMaXCardType(int cardType) {
        this.maxCardType = cardType;
        this.maxCardTypeString = CardUtil.cardTypeToString(cardType);
    }

    public int getMaxCardType() {
        return this.maxCardType;
    }

    public void setMaxCardValue(int cardValue) {
        this.maxCardValue = cardValue;
        this.maxCardValueString = CardUtil.cardValueToString(cardValue);
    }

    public int getMaxCardValue() {
        return this.maxCardValue;
    }

    /***
     * Generate the readable string of the result.
     *
     * @return
     */
    @Override
    public String toString() {
        String result;

        if (this.niu == 0) {
            result = String.format("No niu, max card %s %s", this.maxCardTypeString, this.maxCardValueString);
        } if (this.niu == 10) {
            result = String.format("Niu niu, max card %s %s", this.maxCardTypeString, this.maxCardValueString);
        }else {
            result = String.format("Niu %d, max card %s %s", this.niu, this.maxCardTypeString, this.maxCardValueString);
        }

        return result;
    }

    /***
     * Check if the result is larger than the other one.
     *
     * @param other
     * @return
     */
    public boolean larger(NiuResult other) {

        if (this.niu > other.niu) {
            return true;
        } else if (this.niu < other.niu) {
            return false;
        } else {
            // The niu is equal
            if (this.maxCardValue > other.maxCardValue) {
                return true;
            } else if (this.maxCardValue > other.maxCardValue) {
                return false;
            } else {
                // The niu and card value is equal
                if (this.maxCardType > other.maxCardType) {
                    return true;
                } else if (this.maxCardType < other.maxCardType) {
                    return false;
                } else {
                    // If they are equal, then return false as well
                    return false;
                }
            }
        }
    }

    public boolean largerOrEqual(NiuResult other) {
        // TODO: Merge with the following function
        if (this.niu > other.niu) {
            return true;
        } else if (this.niu < other.niu) {
            return false;
        } else {
            // The niu is equal
            if (this.maxCardValue > other.maxCardValue) {
                return true;
            } else if (this.maxCardValue > other.maxCardValue) {
                return false;
            } else {
                // The niu and card value is equal
                if (this.maxCardType < other.maxCardType) {
                    return true;
                } else if (this.maxCardType > other.maxCardType) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

}

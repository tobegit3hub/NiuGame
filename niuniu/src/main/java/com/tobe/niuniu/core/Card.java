package com.tobe.niuniu.core;

import java.util.Random;

/***
 * The object of Card represent one real card in the game.
 */
public class Card {

    public final static int ALL_CARD_NUMBER = 52;
    public final static int TYPE_CARD_NUMBER = 13;

    // The range is from 0 to 51, which represent all the cards from Spade-A to Diamond-K.
    private int index = 0;

    public Card(int index) {
        this.index = index;
    }

    /***
     * Return the literal value of the card, which are 1 to 10 including J, Q, K.
     *
     * @return
     */
    public int tValue() {
        int value = this.index % TYPE_CARD_NUMBER + 1;
        // Handle 11, 12, 13 which is for J, Q, K
        if (value > 10) {
            value = 10;
        }

        return value;
    }

    /***
     * Return the comparable value of the value, which are 1 to 10 and J, Q, K.
     *
     * @return
     */
    public int comparableValue() {
        return this.index % TYPE_CARD_NUMBER + 1;
    }

    /***
     * Return the printable value.
     * For example, 11 is J and return "J" here.
     *
     * @return
     */
    public String printableValue() {
        int value = this.comparableValue();
        String printableValue = String.valueOf(value);

        if (value == 11) {
            printableValue = "J";
        } else if (value == 12) {
            printableValue = "Q";
        } else if (value == 13) {
            printableValue = "K";
        }

        return printableValue;
    }

    /***
     * Return the type of the card. 0 for Spade(♠), 1 for Heart(♥), 2 for Club(♣), 3 for Diamond(♦).
     *
     * @return
     */
    public int cardType() {
        return this.index / TYPE_CARD_NUMBER;
    }

    /***
     * Return the readable string of the card.
     *
     * @return
     */
    public String cardTypeString() {
        int cardType = this.cardType();
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

    /**
     * Generate the readable string of the card.
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("The card is %s %s", this.cardTypeString(), this.printableValue());
    }

    /***
     * Generate the random card.
     *
     * @return
     */
    public static Card genRandomCard() {
        Random random = new Random();
        return new Card(random.nextInt(42));
    }

    // Test main function
    public static void main(String[] argv) {
        //int cardIndex = 5;
        //System.out.println(new Card(cardIndex));

        System.out.println(Card.genRandomCard());
    }

}
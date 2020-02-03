package com.tobe.niuniu.core;

import java.util.ArrayList;
import java.util.List;

/***
 * The object of Player represent one real player in the game which has five cards.
 */
public class Player {

    public final static int PLAYER_CARD_NUMBER = 5;

    private List<Card> cards = null;

    public Player(List<Card> cards) {
        this.cards = cards;
    }

    public Player(int[] cardIndexs) {
        // Check if the number of parameters is five
        if (cardIndexs.length != PLAYER_CARD_NUMBER) {
            System.out.println("The number of cards is not equal to " + PLAYER_CARD_NUMBER);
        }

        // Initialize object of cards with numbers
        this.cards = new ArrayList<Card>(PLAYER_CARD_NUMBER);
        for (int i=0; i < PLAYER_CARD_NUMBER; ++i) {
            this.cards.add(new Card(cardIndexs[i]));
        }
    }

    /***
     * Generate the readable string of Player which print all the cards.
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Player cards: \n");
        for (Card card: this.cards) {
            stringBuilder.append(card.toString() + "\n");
        }
        return stringBuilder.toString();
    }


    public boolean has_niu() {
        return false;
    }

    public int what_niu() {
        return 0;
    }


    public static void main(String[] argv) {

        int[] cardIndexs = new int[]{1, 21, 31, 42, 5};

        Player player1 = new Player(cardIndexs);

        System.out.println(player1.toString());
    }
    
}

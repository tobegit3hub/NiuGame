package com.tobe.niuniu.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * The object of Player represent one real player in the game which has five cards.
 */
public class Player {

    public final static int PLAYER_CARD_NUMBER = 5;

    private List<Card> cards = null;

    // This is used for combination algorithm
    private static Stack<Integer> selectedStack = new Stack<Integer>();

    private NiuResult niuResult = new NiuResult();

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

        stringBuilder.append("Result: " + niuResult.toString());
        return stringBuilder.toString();
    }

    public NiuResult computeResult() {
        combineAlgorithm(0, 0, 3, PLAYER_CARD_NUMBER);
        return this.niuResult;
    }

    public static void main(String[] argv) {
        int[] cardIndexs = new int[]{0, 6, 3, 24, 1};
        Player player = new Player(cardIndexs);

        NiuResult result = player.computeResult();
        System.out.println(player);
    }

    private void combineAlgorithm(int currentSelectedNumber, int nextSelectableValue, int numberToSelect, int maxAvaibleValue){
        if(currentSelectedNumber == numberToSelect){
            // System.out.println(selectedStack);
            handleCombination();
            return;
        }

        for(int i = nextSelectableValue; i < maxAvaibleValue; ++i) {
            selectedStack.push(i);
            combineAlgorithm(currentSelectedNumber + 1, i + 1, numberToSelect, maxAvaibleValue);
            selectedStack.pop();
        }
    }

   private void handleCombination() {

       NiuResult currentNiuResult = new NiuResult();

       int cardIndex0 = selectedStack.get(0);
       int cardIndex1 = selectedStack.get(1);
       int cardIndex2 = selectedStack.get(2);

       // Get the other indexes
       int cardIndex3 = -1;
       int cardIndex4 = -1;
       for (int i = 0; i < PLAYER_CARD_NUMBER; ++i) {
           if (cardIndex3 == -1 && i != cardIndex0 && i != cardIndex1 && i != cardIndex2) {
               cardIndex3 = i;
           }

           if (cardIndex4 == -1 && i != cardIndex0 && i != cardIndex1 && i != cardIndex2 && i != cardIndex3) {
               cardIndex4 = i;
           }
       }

       Card card0 = this.cards.get(cardIndex0);
       Card card1 = this.cards.get(cardIndex1);
       Card card2 = this.cards.get(cardIndex2);
       Card card3 = this.cards.get(cardIndex3);
       Card card4 = this.cards.get(cardIndex4);

       int threeCardCount = card0.computeValue() + card1.computeValue() + card2.computeValue();
       if (threeCardCount % 10 == 0) {
           int niu = (card3.computeValue() + card4.computeValue()) % 10;
           // Set 10 as niu niu
           if (niu == 0) {
               niu = 10;
           }
           currentNiuResult.setNiu(niu);
       } else {
           currentNiuResult.setNiu(0);
       }

       for (Card card: this.cards) {
           if (card.comparableValue() > currentNiuResult.getMaxCardValue()) {
               currentNiuResult.setMaxCardValue(card.comparableValue());
               currentNiuResult.setMaXCardType(card.cardType());
           } else if (card.comparableValue() == currentNiuResult.getMaxCardValue()) {
               // Notice that the max card type is spade
               if (card.cardType() < currentNiuResult.getMaxCardType()) {
                   currentNiuResult.setMaXCardType(card.cardType());
               }
           }
       }

       if (this.niuResult == null || currentNiuResult.larger(this.niuResult)) {
           this.niuResult = currentNiuResult;
       }
   }
    
}

package com.tobe.niuniu.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * The object of NiuGame represent one real game of dealer or one player.
 */
public class NiuGame {

    public final static String ROLE_DEALER = "ROLE_DEALER";
    public final static String ROLE_PLAYER = "ROLE_PLAYER";
    public final static int ALL_CARD_NUMBER = 52;

    private List<Card> allCards = new ArrayList<Card>(Card.ALL_CARD_NUMBER);
    private String role;
    private int playerNumber;
    private Player player;

    private static Stack<Integer> selectedStack = new Stack<Integer>();

    public NiuGame() {

    }

    public NiuGame(String role, Player player, int playerNumber) {
        this.role = role;
        this.player = player;
        this.playerNumber = playerNumber;
    }

    public NiuGame(String role, Player player) {
        this(role, player, 1);
    }

    public void printAllProbability() {
        StringBuilder stringBuilder = new StringBuilder("Print all probability: \n");

        combineAlgorithm(0, 0, Player.PLAYER_CARD_NUMBER, ALL_CARD_NUMBER);
    }

    public void printWinProbability() {

    }

    public static void main(String[] argv) {

        // int[] cardIndexs = new int[]{1, 21, 31, 42, 5};
        // Player player = new Player(cardIndexs);
        // NiuGame game = new NiuGame(NiuGame.ROLE_PLAYER, player);

        NiuGame game = new NiuGame();

        game.printAllProbability();

        //System.out.println("User cards: " + player);
    }


    private void combineAlgorithm(int currentSelectedNumber, int nextSelectableValue, int numberToSelect, int maxAvaibleValue){
        if(currentSelectedNumber == numberToSelect){
            System.out.println(selectedStack);
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


        int card0 = selectedStack.get(0);
        int card1 = selectedStack.get(1);
        int card2 = selectedStack.get(2);
        int card3 = selectedStack.get(3);
        int card4 = selectedStack.get(4);


        Player player = new Player(new int[]{card0, card1, card2, card3, card4});
        NiuResult result = player.computeResult();
        System.out.println(player);

    }

}

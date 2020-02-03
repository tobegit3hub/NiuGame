package com.tobe.niuniu.core;

import java.util.ArrayList;
import java.util.List;

/***
 * The object of NiuGame represent one real game of dealer or one player.
 */
public class NiuGame {

    public final static String ROLE_DEALER = "ROLE_DEALER";
    public final static String ROLE_PLAYER = "ROLE_PLAYER";

    private List<Card> allCards = new ArrayList<Card>(Card.ALL_CARD_NUMBER);
    private String role;
    private int playerNumber;
    private Player player;

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
        //System.out.println(stringBuilder.toString());

        long combineNumber = 52 * 51 * 50 * 49 * 48; // 311875200
        int counter = 0;

        System.out.println("For start");
        for (int i = 0; i < combineNumber; ++i) {
            counter += 1;
        }

        System.out.println("For end");
    }

    public void printWinProbability() {

    }

    public static void main(String[] argv) {

        int[] cardIndexs = new int[]{1, 21, 31, 42, 5};
        Player player = new Player(cardIndexs);

        NiuGame game = new NiuGame(NiuGame.ROLE_PLAYER, player);

        game.printAllProbability();

        //System.out.println("User cards: " + player);
    }

}

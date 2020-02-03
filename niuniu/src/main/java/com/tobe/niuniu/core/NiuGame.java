package com.tobe.niuniu.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * The object of NiuGame represent one real game of dealer or one player.
 */
public class NiuGame {

    public final static int ALL_CARD_NUMBER = 52;
    public final static int ALL_CARD_COMBINATION = 2598960;
    public final static int CARD_COMBINATION_WITHOUT_PLAYER = 1065021; // 2598960 - 1533939

    private Player player = null;
    private NiuResult playerResult = null;
    private int playerWinCount = 0;

    private static Stack<Integer> selectedStack = new Stack<Integer>();

    // The count of all the cards from niu 1 to niu niu
    private Map<Integer, Integer> allNiuCount = new HashMap<Integer, Integer>();

    {
        allNiuCount.put(0, 0);
        allNiuCount.put(1, 0);
        allNiuCount.put(2, 0);
        allNiuCount.put(3, 0);
        allNiuCount.put(4, 0);
        allNiuCount.put(5, 0);
        allNiuCount.put(6, 0);
        allNiuCount.put(7, 0);
        allNiuCount.put(8, 0);
        allNiuCount.put(9, 0);
        allNiuCount.put(10, 0);

    }

    public NiuGame() {

    }

    public NiuGame(Player player) {
        this.player = player;
    }

    public void printAllProbability() {

        combineAlgorithm(0, 0, Player.PLAYER_CARD_NUMBER, ALL_CARD_NUMBER);

        StringBuilder stringBuilder = new StringBuilder("The probability of NiuNiu: \n");
        for (int i = 0; i <= 10; ++i) {
            double probability = this.allNiuCount.get(i) * 100.0 / ALL_CARD_COMBINATION;

            if (i == 0) {
                stringBuilder.append(String.format("No niu: count %d, percentage %f%% \n", this.allNiuCount.get(i), probability));
            } else if (i == 10) {

                int jqkNiuniuCount = 4368;
                double jqkNiuniuProbability = jqkNiuniuCount * 100.0 / ALL_CARD_COMBINATION;

                int normalNiuniuCount = this.allNiuCount.get(i) - 4368;
                double normalNiuniuProbability = normalNiuniuCount * 100.0 / ALL_CARD_COMBINATION;

                stringBuilder.append(String.format("Niu niu: count %d, percentage %f%% \n", normalNiuniuCount, normalNiuniuProbability));
                stringBuilder.append(String.format("Niu niu(JQK): count %d, percentage %f%% \n", jqkNiuniuCount, jqkNiuniuProbability));
            } else {
                stringBuilder.append(String.format("Niu %d: count %d, percentage %f%% \n", i, this.allNiuCount.get(i), probability));
            }
        }
        System.out.println(stringBuilder.toString());
    }

    public void printWinProbability() {
        if (this.player == null) {
            System.out.println("Error, the player should not be null");
            System.exit(-1);
        }

        this.playerResult = this.player.computeResult();

        combineAlgorithmForPlayer(0, 0, Player.PLAYER_CARD_NUMBER, ALL_CARD_NUMBER);

        System.out.println(this.player);
        System.out.println(String.format("Win probability: %f%%", this.playerWinCount * 100.0 / ALL_CARD_COMBINATION));
    }

    public static void main(String[] argv) {

        // NiuGame game = new NiuGame();
        // game.printAllProbability();

        Player player = new Player(new int[]{12, 21, 31, 43, 23});
        NiuGame game = new NiuGame(player);
        game.printWinProbability();

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

        int card0 = selectedStack.get(0);
        int card1 = selectedStack.get(1);
        int card2 = selectedStack.get(2);
        int card3 = selectedStack.get(3);
        int card4 = selectedStack.get(4);

        Player player = new Player(new int[]{card0, card1, card2, card3, card4});
        NiuResult result = player.computeResult();
        //System.out.println(result);

        increaseNiuCount(result.getNiu());
    }

    private void increaseNiuCount(int niu) {
        this.allNiuCount.put(niu, this.allNiuCount.get(niu) + 1);
    }

    private void combineAlgorithmForPlayer(int currentSelectedNumber, int nextSelectableValue, int numberToSelect, int maxAvaibleValue){
        if(currentSelectedNumber == numberToSelect){
            compareWithPlayer();
            return;
        }

        for(int i = nextSelectableValue; i < maxAvaibleValue; ++i) {
            selectedStack.push(i);
            combineAlgorithmForPlayer(currentSelectedNumber + 1, i + 1, numberToSelect, maxAvaibleValue);
            selectedStack.pop();
        }
    }

    private void compareWithPlayer() {

        int card0 = selectedStack.get(0);
        int card1 = selectedStack.get(1);
        int card2 = selectedStack.get(2);
        int card3 = selectedStack.get(3);
        int card4 = selectedStack.get(4);

        Player player = new Player(new int[]{card0, card1, card2, card3, card4});
        NiuResult result = player.computeResult();

        if (this.playerResult.largerOrEqual(result)) {
            this.playerWinCount += 1;
        }

    }

}

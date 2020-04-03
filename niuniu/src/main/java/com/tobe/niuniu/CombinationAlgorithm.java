package com.tobe.niuniu;

import java.util.Stack;


public class CombinationAlgorithm {

    private static Stack<Integer> selectedStack = new Stack<Integer>();
    private static int count;

    public static void combineAlgorithm(int currentSelectedNumber, int nextSelectableValue, int numberToSelect, int maxAvaibleValue){
        if(currentSelectedNumber == numberToSelect){
            //System.out.println(selectedStack);
            count += 1;
            return;
        }

        for(int i = nextSelectableValue; i < maxAvaibleValue; ++i) {
            selectedStack.push(i);
            combineAlgorithm(currentSelectedNumber + 1, i + 1, numberToSelect, maxAvaibleValue);
            selectedStack.pop();
        }
    }

    public static void main(String[] argv){
        combineAlgorithm(0, 0, 5, 52);
        System.out.println("Count is " + count);
    }

}

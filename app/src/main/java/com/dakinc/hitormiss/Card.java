package com.dakinc.hitormiss;

/**
 * Created by A on 2017-01-28.
 */

public class Card {
    private int value;
    private int suit;

    private static String values[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
    private static String suits[] = {"Diamonds", "Clubs", "Hearts", "Spades"};

    public int getValue() {
        return this.value;
    }

    public int getSuit() {
        return this.suit;
    }

    public void Card(int Value) {
        this.value = Value;
    }
}

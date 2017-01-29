package com.dakinc.hitormiss;

import java.io.Serializable;

/**
 * Created by A on 2017-01-28.
 */

public class Card implements Serializable {
//    private String value;
//    private int suit;
    private int value;
    private String name;
//    private static int values[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
//    private static String names[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
//    private static String values[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
//    private static String suits[] = {"Diamonds", "Clubs", "Hearts", "Spades"};

//    public final static int SPADES = 0;   // Codes for the 4 suits, plus Joker.
//    public final static int HEARTS = 1;
//    public final static int DIAMONDS = 2;
//    public final static int CLUBS = 3;
//
//    public final static int ACE = 1;      // Codes for the non-numeric cards.
//    public final static int JACK = 11;    //   Cards 2 through 10 have their
//    public final static int QUEEN = 12;   //   numerical values for their codes.
//    public final static int KING = 13;

    public Card(int value) {
        this.value = value;
        this.name = Integer.toString(value);
    }

    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

//    public Card(int Value) {
//        this.value = valueToString(Value);
//    }

    public int getValue() {
        return this.value;
    }

//    public int getSuit() {
//        return this.suit;
//    }

//    public String suitToString() {
//        switch (suit) {
//            case SPADES:
//                return suits[3];
//            case HEARTS:
//                return suits[2];
//            case CLUBS:
//                return suits[1];
//            case DIAMONDS:
//                return suits[0];
//        }
//    }

//    public String valueToString(int Value) {
//        if (Value < 1 || Value > 13) {
//            return null;
//        } else {
//            switch ( Value ) {
//                case 1:
//                    return values[0];
//                case 2:
//                    return values[1];
//                case 3:
//                    return values[2];
//                case 4:
//                    return values[3];
//                case 5:
//                    return values[4];
//                case 6:
//                    return values[5];
//                case 7:
//                    return values[6];
//                case 8:
//                    return values[7];
//                case 9:
//                    return values[8];
//                case 10:
//                    return values[9];
//                case 11:
//                    return values[10];
//                case 12:
//                    return values[11];
//                case 13:
//                    return values[12];
//            }
//        }
//        return null;
//    }

    public void setCard(int value) {
        this.value = value;
//        this.valueToString();
//        this.suit = Suit;
    }
}

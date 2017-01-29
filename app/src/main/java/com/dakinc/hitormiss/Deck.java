package com.dakinc.hitormiss;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by A on 2017-01-28.
 */

public class Deck implements Serializable {

    private ArrayList<Card> deck;
    private Card card;
    private int AceCount;

//    public Deck(int deckCount) {
//        deck = new ArrayList<Card>();
//        for (int i = 0; i < deckCount; i++){
//            for (int cardValue = 1; cardValue <= 13; cardValue++) {
//                for (int suit = 0; suit <= 3; suit++) {
//                    card = new Card(cardValue);
//                    deck.add(card);
//                }
//            }
//        }
//    }

    public Deck(int deckCount) {
        deck = new ArrayList<Card>();
        for (int i = 0; i < deckCount; i++){
            for (int cardValue = 2; cardValue <= 10; cardValue++) {
                for (int suit = 0; suit <= 3; suit++) {
                    card = new Card(cardValue);
                    deck.add(card);
                }
            }
            for (int suit = 0; suit <= 3; suit++) {
                card = new Card(10, "J");
                deck.add(card);
                card = new Card(10, "Q");
                deck.add(card);
                card = new Card(10, "K");
                deck.add(card);
                card = new Card(11, "Ace");
                deck.add(card);
                card = new Card(1, "Ace");
                deck.add(card);
            }
        }
        this.AceCount = deckCount * 4;
    }

//    public int equalString(int Value) {
//        card = new Card(Value);
//        for (int i = 0; i < deck.size(); i++) {
//            if (deck.get(i).getValue().equals(card.getValue())) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public int findCard(int value) {
        for (int i = 0; i < this.deckSize() + this.AceCount; i++) {
            if (deck.get(i).getValue() == value) {
                return i;
            }
        }
        return -1;
    }

//    public void dealCard() {
//        deck.;
//        deck.
//    }

//    public void shuffle() {
//
//    }

    public int removeCard(int cardValue) {
//        deck.remove(card);
//        card = new Card(cardValue);

        int index = findCard(cardValue);
        if (index == -1) {
            return -1;
        }
        else {
            if (cardValue == 1 | cardValue == 11) {
                this.deck.remove(index);
                if (cardValue == 1) {
                    index = findCard(11);
                    this.deck.remove(index);
                }
                else {
                    index = findCard(1);
                    this.deck.remove(index);
                }
                this.AceCount -= 1;
            }
            else {
                this.deck.remove(index);
            }
            return 0;
        }

//        deck.remove(card);
//        deck.remove
    }

    public int remainingCopies(int cardValue) {
        int copyCount = 0;
        for (int i = 0; i < (this.deckSize() + this.AceCount); i++) {
            if (deck.get(i).getValue() == cardValue) {
                copyCount += 1;
            }
        }
        return copyCount;
    }

    public int deckSize() {
        return (this.deck.size() - this.AceCount);
    }

//    public int cardProbability(Card card) {
//        return remainingCopies(card) / deckSize();
//    }
    public Double cardProbability(int cardValue) {
//        card.setCard(cardValue);
//        card = new Card(cardValue);
//        Double size = deckSize();
        return ((double) remainingCopies(cardValue) / (double) deckSize());
    }

    public Double maxProbability(int maxValueCard) {
        if (maxValueCard < 0) {
            return 0.0;
        }

        Double probabilitySum = 0.0;

        for (int i = 1; i <= maxValueCard; i++) {
            probabilitySum += cardProbability(i);
        }
        return probabilitySum;
    }

    public Double minProbability(int maxValueCard) {
        if (maxValueCard < 0) {
            return 0.0;
        }
        return (1.0 - maxProbability(maxValueCard));
    }

    public Double subSeventeen(int faceUpValue) {

        if (17-faceUpValue > 11) {
            return 1.0;
        }
        else {
            return maxProbability(17 - faceUpValue);
        }
    }

    public Double seventeenUp(int faceUpValue) {

        return (1.0 - subSeventeen(faceUpValue));
    }

    public Double weightedAverage(int start, int end) {
        int total = 0;
        int count = 0;
        for (int i = 0; i < this.deckSize() + this.AceCount; i++) {
            if (end >= this.deck.get(i).getValue() && this.deck.get(i).getValue() >= start) {
                total += this.deck.get(i).getValue();
                count += 1;
            }
        }
        return (double) total/count;
    }

    public void removeClosest(int value) {
        int range = 0;
        while(true) {
            if (this.removeCard(value + range) != -1) {
                break;
            }
            if (this.removeCard(value - range) != -1) {
                break;
            }
            range += 1;
        }
    }
    // function to get the average outcome value of the hands, ie like average expected hand value is 17.5
    // rounding when you don't have all of that cardvalue remaining
}

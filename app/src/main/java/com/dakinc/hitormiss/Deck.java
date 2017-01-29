package com.dakinc.hitormiss;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.R.id.list;

/**
 * Created by A on 2017-01-28.
 */

public class Deck {

    private ArrayList<Card> deck;
    private Card card;
    private int probabilitySum;

    public Deck(int deckCount) {
        deck = new ArrayList<Card>();
        for (int i = 0; i < deckCount; i++){
            for (int cardValue = 1; cardValue <= 13; cardValue++) {
                for (int suit = 0; suit <= 3; suit++) {
                    card = new Card(cardValue);
                    deck.add(card);
                }
            }
        }
    }

//    public void dealCard() {
//        deck.;
//        deck.
//    }

//    public void shuffle() {
//
//    }

    public void removeCard(int cardValue) {
//        deck.remove(card);
        card = new Card(cardValue);
        deck.remove(card);
    }

    public int remainingCopies(int cardValue) {
        int copyCount = 0;
        card = new Card(cardValue);
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).equals(card)) {
                copyCount += 1;
            }
        }
        return copyCount;
    }

    public int deckSize() {
        return deck.size();
    }

//    public int cardProbability(Card card) {
//        return remainingCopies(card) / deckSize();
//    }
    public int cardProbability(int cardValue) {
//        card.setCard(cardValue);
//        card = new Card(cardValue);
        return (remainingCopies(cardValue) / deckSize());
    }

    public int safeProbability(int maxValueCard) {

        for (int i = 1; i < maxValueCard + 1; i++) {
            probabilitySum += cardProbability(i);
        }
        return probabilitySum;
    }

    public int bustProbability(int maxValueCard) {
        return (1 - safeProbability(maxValueCard));
    }

    // function to get the average outcome value of the hands, ie like average expected hand value is 17.5
    // rounding when you don't have all of that cardvalue remaining
}

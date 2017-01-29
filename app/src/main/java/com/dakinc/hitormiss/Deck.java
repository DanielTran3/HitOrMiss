package com.dakinc.hitormiss;

/**
 * Created by A on 2017-01-28.
 */

public class Deck {

    public Card deck[];

    public void deck(int deckCount) {
        deck = new Card[52];
        for (int i = 0; i < deckCount; i++){
            for (int card = 1; card <= 13; card++) {
                for (int suit = 0; suit < 3; suit++) {
                    Deck.add(card, suit);
                }
            }
        }
    }

    public void dealCard() {
        deck.pop();
    }

    public void shuffle() {

    }

    public void removeCard() {

    }
}

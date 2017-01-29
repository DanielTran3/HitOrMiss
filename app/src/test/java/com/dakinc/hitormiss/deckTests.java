package com.dakinc.hitormiss;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by A on 2017-01-28.
 */

public class deckTests {

    private Card card;
    private Deck deck;
    @Before
    public void setup() {
    }

    @Test
    public void testCards() {
//        card.setCard(1);
        card = new Card(11, "Ace");
//        System.out.println(card.getValue());
        assertTrue(card.getValue() == 11);
    }

    @Test
    public void testProbability() {
        deck = new Deck(1);
//        deck.
//        System.out.println(deck.get(1));
//        card = new Card(1);
        assertTrue((deck.remainingCopies(1)) == 4);
        System.out.println("VALUE IS " + deck.cardProbability(1));
        assertTrue(deck.cardProbability(1) == (1.0/13.0));
//        System.out.println(deck.get(1));
        deck.removeCard(11);
//        System.out.println(deck.remainingCopies(1));
        assertTrue((deck.remainingCopies(1)) == 3);
//        System.out.println(deck.cardProbability(1));
//        System.out.println(3.00/51.00);
        assertTrue(deck.cardProbability(1) ==  (3.0/51.0));
    }

    @Test
    public void testBust() {
        deck = new Deck(1);
        deck.removeCard(5);
        deck.removeCard(10);
        System.out.println(deck.maxProbability(6));
        System.out.println(deck.minProbability(6));
    }

    @Test
    public void testSubSeventeen() {
        deck = new Deck(1);
        deck.removeCard(9);
        System.out.println(deck.subSeventeen(9));
    }

    @Test
    public void testSeventeenUp() {
        deck = new Deck(1);
        deck.removeCard(9);
        System.out.println(deck.minProbability(8));
        System.out.println(deck.seventeenUp(9));
    }
}

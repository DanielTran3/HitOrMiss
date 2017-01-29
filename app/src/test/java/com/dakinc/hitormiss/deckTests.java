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
        card = new Card(1);
//        System.out.println(card.getValue());
        assertTrue(card.getValue().equals("Ace"));
    }

    public void testProbability() {
        deck = new Deck(1);
        card = new Card(1);
        assertTrue((deck.remainingCopies(1)) == 4);
        assertTrue(deck.cardProbability(1) == (1/13));
        deck.removeCard(1);
        assertTrue((deck.remainingCopies(1)) == 3);
        assertTrue(deck.cardProbability(1) == (3/51));

    }
}

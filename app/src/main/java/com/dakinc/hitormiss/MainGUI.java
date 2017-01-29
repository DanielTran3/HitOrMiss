package com.dakinc.hitormiss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainGUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gui);

        Deck real_deck = new Deck(3);

        // Player's faceup total
        int player_total = 1111111;

        // Dealer's faceup total (1 card)
        int dealer_total = 1111111;

        ///// Removal of Cards from deck
        real_deck.removeCard(11111);
        real_deck.removeCard(11111);
        real_deck.removeCard(11111);

        /// Calculate % of what dealer might get
        Double lowerbound_prob = real_deck.subSeventeen(dealer_total);
        Double upperbound_prob = real_deck.seventeenUp(dealer_total);

        Double weighted_average;
        int threshold = 0;

        if (upperbound_prob > lowerbound_prob) {
            // Sequence #1
            weighted_average = real_deck.weightedAverage(17 - dealer_total, 21 - dealer_total);
            threshold = (int) Math.ceil(weighted_average) + dealer_total;
            Deck fake_deck = (Deck) deepClone(real_deck);
            fake_deck.removeClosest((int) Math.ceil(weighted_average));
            while(true) {
                if (player_total >= threshold) {
                    // STAY
                    break;
                } else {
                    if (fake_deck.minProbability(21 - player_total) > fake_deck.minProbability(player_total - dealer_total)) {
                        // STAY
                        break;
                    } else {
                        if (fake_deck.minProbability(21 - player_total) > 0.5) {
                            // STAY
                            break;
                        }
                        else {
                            // HIT
                            if (player_total > 21) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        else {
            while(true) {
                // Sequence #2
                weighted_average = real_deck.weightedAverage(1, 16 - dealer_total);
                threshold = (int) Math.ceil(weighted_average) + dealer_total;
                Deck fake_deck = (Deck) deepClone(real_deck);
                fake_deck.removeClosest((int) Math.ceil(weighted_average));

                //Sequence #3
                while(true) {
                    if (threshold > player_total) {
                        while(true) {
                            if (fake_deck.minProbability(21 - player_total) > fake_deck.minProbability(player_total - dealer_total)) {
                                // STAY
                                break;
                            } else {
                                if (fake_deck.minProbability(21 - player_total) > 0.5) {
                                    // STAY
                                    break;
                                }
                                else {
                                    // HIT
                                    if (player_total > 21) {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    else {
                        if (fake_deck.minProbability(21 - dealer_total) >= 0.5) {
                            // STAY
                            break;
                        }
                        else {
                            lowerbound_prob = fake_deck.subSeventeen(dealer_total);
                            upperbound_prob = fake_deck.maxProbability(21 - dealer_total);
                            if (upperbound_prob > lowerbound_prob) {
                                // Sequence #1
                                weighted_average = fake_deck.weightedAverage(17 - dealer_total, 21 - dealer_total);
                                threshold += (int) Math.ceil(weighted_average);
                                fake_deck.removeClosest((int) Math.ceil(weighted_average));
                                while (true) {
                                    if (player_total >= threshold) {
                                        // STAY
                                        break;
                                    } else {
                                        if (fake_deck.minProbability(21 - player_total) > fake_deck.minProbability(player_total - dealer_total)) {
                                            // STAY
                                            break;
                                        } else {
                                            if (fake_deck.minProbability(21 - player_total) > 0.5) {
                                                // STAY
                                                break;
                                            }
                                            else {
                                                // HIT
                                                if (player_total > 21) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

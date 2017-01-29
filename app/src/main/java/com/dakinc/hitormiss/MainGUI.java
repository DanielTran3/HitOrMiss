package com.dakinc.hitormiss;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.input.SearchClause;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.SearchHit;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.model.output_info.ConceptOutputInfo;
import clarifai2.dto.prediction.Concept;
import clarifai2.dto.prediction.Prediction;
import okhttp3.OkHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainGUI extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    TextView displayPlayer;
    TextView displayDealer;
    TextView hitOrStay;
    String playerCards = "";
    ImageButton cameraButton;
    ArrayList<Integer> cardsOnBoard = new ArrayList<>();
//    int cardCounter = 0;
    int playerCount = 0;
    Boolean player = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gui);

        try {
            ServerProcessing.SetUpModelTask newModel = new ServerProcessing.SetUpModelTask();
            newModel.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        displayPlayer = (TextView) findViewById(R.id.PlayerCards);
        displayDealer = (TextView) findViewById(R.id.DealerCards);
        hitOrStay = (TextView) findViewById(R.id.details);
//        cameraButton = (ImageButton) findViewById(R.id.imageButton);
//        Deck real_deck = new Deck(3);
//
//        cameraButton.performClick();
//        // Dealer's faceup total (1 card)
//        int dealer_total = cardsOnBoard.get(cardCounter - 1);
//        real_deck.removeCard(dealer_total);
//        Toast.makeText(MainGUI.this, Integer.toString(dealer_total) ,Toast.LENGTH_SHORT).show();
//
//        cameraButton.performClick();
//        // Player's faceup total
//        int player_total = cardsOnBoard.get(cardCounter - 1);
//        real_deck.removeCard(cardsOnBoard.get(cardCounter - 1));
//        Toast.makeText(MainGUI.this, Integer.toString(dealer_total) ,Toast.LENGTH_SHORT).show();
//
//        cameraButton.performClick();
//        player_total += cardsOnBoard.get(cardCounter - 1);
//        real_deck.removeCard(cardsOnBoard.get(cardCounter - 1));
//        Toast.makeText(MainGUI.this, Integer.toString(dealer_total) ,Toast.LENGTH_SHORT).show();
//
//
//        ///// Removal of Cards from deck
//
//        /// Calculate % of what dealer might get
//        Double lowerbound_prob = real_deck.subSeventeen(dealer_total);
//        Double upperbound_prob = real_deck.seventeenUp(dealer_total);
//
//        Double weighted_average;
//        int threshold = 0;
//
//        if (upperbound_prob > lowerbound_prob) {
//            // Sequence #1
//            weighted_average = real_deck.weightedAverage(17 - dealer_total, 21 - dealer_total);
//            threshold = (int) Math.ceil(weighted_average) + dealer_total;
//            Deck fake_deck = (Deck) deepClone(real_deck);
//            fake_deck.removeClosest((int) Math.ceil(weighted_average));
//            while(true) {
//                if (player_total >= threshold) {
//                    // STAY
//                    break;
//                } else {
//                    if (fake_deck.minProbability(21 - player_total) > fake_deck.minProbability(player_total - dealer_total)) {
//                        // STAY
//                        break;
//                    } else {
//                        if (fake_deck.minProbability(21 - player_total) > 0.5) {
//                            // STAY
//                            break;
//                        }
//                        else {
//                            // HIT
//
//                            if (player_total > 21) {
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        else {
//            while(true) {
//                // Sequence #2
//                weighted_average = real_deck.weightedAverage(1, 16 - dealer_total);
//                threshold = (int) Math.ceil(weighted_average) + dealer_total;
//                Deck fake_deck = (Deck) deepClone(real_deck);
//                fake_deck.removeClosest((int) Math.ceil(weighted_average));
//
//                //Sequence #3
//                while(true) {
//                    if (threshold > player_total) {
//                        while(true) {
//                            if (fake_deck.minProbability(21 - player_total) > fake_deck.minProbability(player_total - dealer_total)) {
//                                // STAY
//                                break;
//                            } else {
//                                if (fake_deck.minProbability(21 - player_total) > 0.5) {
//                                    // STAY
//                                    break;
//                                }
//                                else {
//                                    // HIT
//                                    if (player_total > 21) {
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                        break;
//                    }
//                    else {
//                        if (fake_deck.minProbability(21 - dealer_total) >= 0.5) {
//                            // STAY
//                            break;
//                        }
//                        else {
//                            lowerbound_prob = fake_deck.subSeventeen(dealer_total);
//                            upperbound_prob = fake_deck.maxProbability(21 - dealer_total);
//                            if (upperbound_prob > lowerbound_prob) {
//                                // Sequence #1
//                                weighted_average = fake_deck.weightedAverage(17 - dealer_total, 21 - dealer_total);
//                                threshold += (int) Math.ceil(weighted_average);
//                                fake_deck.removeClosest((int) Math.ceil(weighted_average));
//                                while (true) {
//                                    if (player_total >= threshold) {
//                                        // STAY
//                                        break;
//                                    } else {
//                                        if (fake_deck.minProbability(21 - player_total) > fake_deck.minProbability(player_total - dealer_total)) {
//                                            // STAY
//                                            break;
//                                        } else {
//                                            if (fake_deck.minProbability(21 - player_total) > 0.5) {
//                                                // STAY
//                                                break;
//                                            }
//                                            else {
//                                                // HIT
//                                                if (player_total > 21) {
//                                                    break;
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }
//                break;
//            }
//        }
    }

    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Check which request we're responding to
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
        // If the request was successful, perform image processing
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainGUI.this, "Successfully Took a Photo!",Toast.LENGTH_SHORT).show();
                Bitmap picture = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
//                byte[] photoToProcess = data.getByteArrayExtra("pred");

//                final List<ClarifaiOutput<Concept>> predictionResults =
//                        client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
//                                .predict()
//                                .withInputs(
//                                        ClarifaiInput.forImage(ClarifaiImage.of(photoToProcess))
//                                )
//                                .executeSync()
//                                .get();

                try {
                    List<ClarifaiOutput<Prediction>> output;
                    ServerProcessing.PredictTask predictionResults = new ServerProcessing.PredictTask();
                    predictionResults.execute(byteArray);
//                    output = predictionResults.get(100, TimeUnit.MILLISECONDS);
//                    List<ClarifaiOutput<Concept>> output;
//                    ServerProcessing.DefaultPredictTask predictionResults = new ServerProcessing.DefaultPredictTask();
//                    predictionResults.execute(byteArray);
                    Thread.sleep(1000);
                    output = predictionResults.get();
                    if (player == false) {
                        if (output.get(0).data().get(0).asConcept().name() == "Ace") {
//                            cardsOnBoard.add(cardCounter, 11);
                        }
                        else if (output.get(0).data().get(0).asConcept().name() == "Jack") {
//                            cardsOnBoard.add(cardCounter, 10);
                        }
                        else if (output.get(0).data().get(0).asConcept().name() == "Queen") {
//                            cardsOnBoard.add(cardCounter, 10);
                        }
                        else if (output.get(0).data().get(0).asConcept().name() == "King") {
//                            cardsOnBoard.add(cardCounter, 10);
                        }
                        else {
//                            cardsOnBoard.add(cardCounter, Integer.parseInt(output.get(0).data().get(0).asConcept().name()));
                        }
//                        cardCounter++;
                        displayDealer.setText(output.get(0).data().get(0).asConcept().name());
                        player = true;
                    }
                    else {
                        if (output.get(0).data().get(0).asConcept().name() == "Ace") {
//                            cardsOnBoard.add(cardCounter, 11);
                            playerCount += 11;
                        }
                        else if (output.get(0).data().get(0).asConcept().name().equals("Jack")) {
//                            cardsOnBoard.add(cardCounter, 10);
                            playerCount += 10;
                        }
                        else if (output.get(0).data().get(0).asConcept().name().equals("Queen")) {
//                            cardsOnBoard.add(cardCounter, 10);
                            playerCount += 10;
                        }
                        else if (output.get(0).data().get(0).asConcept().name().equals("King")) {
//                            cardsOnBoard.add(cardCounter, 10);
                            playerCount += 10;
                        }
                        else {
//                            cardsOnBoard.add(cardCounter, Integer.parseInt(output.get(0).data().get(0).asConcept().name()));
                            playerCount += Integer.parseInt(output.get(0).data().get(0).asConcept().name());
                        }
//                        cardCounter++;
                        playerCards = playerCards + output.get(0).data().get(0).asConcept().name() + " | ";
                        displayPlayer.setText(playerCards);
                        hitOrStay.setText(Integer.toString(playerCount));
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            else {
                    Toast.makeText(MainGUI.this, "Exited Without Taking a Photo.",Toast.LENGTH_SHORT).show();
                }
        }
    }
    public void startCamera(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}



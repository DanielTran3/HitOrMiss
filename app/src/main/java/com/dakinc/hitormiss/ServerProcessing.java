package com.dakinc.hitormiss;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.input.SearchClause;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.model.output_info.ConceptOutputInfo;
import clarifai2.dto.prediction.Concept;
import clarifai2.dto.prediction.Prediction;
import okhttp3.OkHttpClient;

public class ServerProcessing {

    final static String clientID = "lE170UkqyLAsmqIfhE74b6zOarcAft-nw8Mbn7xx";
    final static String clientSecret = "d2WvO1UcnrnINd27PExWcQ3WMsUtRx3X-ClLXlNN";
    final static ClarifaiClient client = new ClarifaiBuilder(clientID, clientSecret)
//            .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
            .buildSync(); // or use .build() to get a Future<ClarifaiClient>
    final static CardCollection cards = new CardCollection();

    public static class SetUpModelTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... search_parameters) {

            try {
//                client.addConcepts().plus(CardCollection.getCollection());
                client.addInputs()
                        .plus(ClarifaiInput.forImage(ClarifaiImage.of("http://imgur.com/a/uz0nc.jpg"))
                                .withConcepts(Concept.forID("8")))
                        .plus(ClarifaiInput.forImage(ClarifaiImage.of("https://usercontent2.hubstatic.com/718481_f260.jpg"))
                                .withConcepts(Concept.forID("4Spades")))
                        .plus(ClarifaiInput.forImage(ClarifaiImage.of("https://usercontent1.hubstatic.com/718512_f260.jpg"))
                                .withConcepts(Concept.forID("10Spades"))).executeSync();

                client.createModel("cards")
                        .withOutputInfo(ConceptOutputInfo.forConcepts(Concept.forID("9Hearts")))
                        .withOutputInfo(ConceptOutputInfo.forConcepts(Concept.forID("4Spades")))
                        .withOutputInfo(ConceptOutputInfo.forConcepts(Concept.forID("10Spades"))).executeSync();

                client.trainModel("cards").executeSync();
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong!");
            }
            return null;
        }
    }

    public static class PredictTask extends AsyncTask<byte[], Void, List<ClarifaiOutput<Prediction>>> {
        @Override
        protected List<ClarifaiOutput<Prediction>> doInBackground(byte[]... image) {

            try {
//                final ClarifaiResponse predictionResults = client.searchInputs(SearchClause.matchImageVisually(ClarifaiImage.of(image[0])))
//                        .getPage(1)
//                        .executeSync();
//                return predictionResults;
                client.getModelByID("cards");
                final List<ClarifaiOutput<Prediction>> predictionResults =
                        client.predict("cards")
                                .withInputs(
                                        ClarifaiInput.forImage(ClarifaiImage.of(image[0]))
                                )
                                .executeSync()
                                .get();
                return predictionResults;
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong!");
                return null;
            }
        }
    }
}

package com.dakinc.hitormiss;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.api.request.input.SearchClause;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.model.output_info.ConceptOutputInfo;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;

public class ServerProcessing {

    final static String clientID = "lE170UkqyLAsmqIfhE74b6zOarcAft-nw8Mbn7xx";
    final static String clientSecret = "d2WvO1UcnrnINd27PExWcQ3WMsUtRx3X-ClLXlNN";

    final static ClarifaiClient client = new ClarifaiBuilder(clientID, clientSecret)
            .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
            .buildSync(); // or use .build() to get a Future<ClarifaiClient>

    public static class SetUpModelTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... search_parameters) {

            try {
                client.addInputs()
                        .plus(ClarifaiInput.forImage(ClarifaiImage.of("https://usercontent2.hubstatic.com/718481_f260.jpg"))
                                .withConcepts(Concept.forID("4Spade"))).executeSync();

                client.createModel("cards")
                        .withOutputInfo(ConceptOutputInfo.forConcepts(Concept.forID("4Spade"))).executeSync();

                client.trainModel("{model_id}").executeSync();
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong!");
            }
            return null;
        }
    }

    public static class PredictTask extends AsyncTask<byte[], Void, List<ClarifaiOutput<Concept>>> {
        @Override
        protected List<ClarifaiOutput<Concept>> doInBackground(byte[]... image) {

            try {
//                final ClarifaiResponse predictionResults = client.searchInputs(SearchClause.matchImageVisually(ClarifaiImage.of(image[0])))
//                        .getPage(1)
//                        .executeSync();
//                return predictionResults;
                final List<ClarifaiOutput<Concept>> predictionResults =
                        client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
                                .predict()
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

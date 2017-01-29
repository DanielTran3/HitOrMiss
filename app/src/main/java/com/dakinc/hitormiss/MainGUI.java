package com.dakinc.hitormiss;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
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

public class MainGUI extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    final String clientID = "lE170UkqyLAsmqIfhE74b6zOarcAft-nw8Mbn7xx";
    final String clientSecret = "d2WvO1UcnrnINd27PExWcQ3WMsUtRx3X-ClLXlNN";

    final ClarifaiClient client = new ClarifaiBuilder(clientID, clientSecret)
            .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
            .buildSync(); // or use .build() to get a Future<ClarifaiClient>

    TextView displayText;
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

//        //Create Model
//        client.createModel("cards")
//                .withOutputInfo(ConceptOutputInfo.forConcepts(Concept.forID("4Spade"))).executeSync();


        //Train
        // All concepts need at least one "positive example" (ie, an input whose image file contains that concept)
        // So we will add a positive and a negative example of Boscoe
//        client.addInputs()
//                .plus(ClarifaiInput.forImage(ClarifaiImage.of("https://usercontent2.hubstatic.com/718481_f260.jpg"))
//                .withConcepts(Concept.forID("cards"))).executeSync();
//
//
//
//
//        //Predict
//        final List<ClarifaiOutput<Concept>> predictionResults =
//                client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
//                        .predict()
//                        .withInputs(
//                                ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/metro-north.jpg"))
//                        )
//                        .executeSync()
//                        .get();
//
//
//        //Search
//        final ClarifaiResponse<List<SearchHit>> trainImages = client.searchInputs(
//                // Finds images that match this picture of a train
//                SearchClause.matchImageVisually(ClarifaiImage.of("https://samples.clarifai.com/metro-north.jpg"))
//        )
//                .getPage(1)
//                .executeSync();
//
//        // Create some concepts
//        client.addConcepts()
//                .plus(
//                        Concept.forID("boscoe")
//                )
//                .executeSync();
//
//
//        // Now that you have created the boscoe concept, and you have positive
//        // examples of this concept, you can create a Model that knows this concept
//        final ConceptModel petsModel = client.createModel("pets")
//                .withOutputInfo(ConceptOutputInfo.forConcepts(
//                        Concept.forID("boscoe")
//                ))
//                .executeSync()
//                .get();
//
//        // Now that your app contains inputs with the concepts that you wanted to
//        // detect, you can train your "pets" model
//        petsModel.train().executeSync();
//
        displayText = (TextView) findViewById(R.id.testText);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Check which request we're responding to
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
        // If the request was successful, perform image processing
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainGUI.this, "Successfully Took a Photo!",Toast.LENGTH_SHORT).show();
                Bitmap test = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                test.compress(Bitmap.CompressFormat.PNG, 100, stream);
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
                    displayText.setText(output.get(0).data().get(0).asConcept().name());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            else {
                    Toast.makeText(MainGUI.this, "Exited Without Taking a Photo.",Toast.LENGTH_SHORT).show();
                }
        }
    }
    public void testCamera(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}



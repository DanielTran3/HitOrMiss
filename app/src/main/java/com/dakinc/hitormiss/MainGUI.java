package com.dakinc.hitormiss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainGUI extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gui);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            // If the request was successful, perform image processing
            if (resultCode == RESULT_OK) {

                Toast.makeText(MainGUI.this, "Successfully Took a Photo!",Toast.LENGTH_SHORT).show();
//                Bitmap photoToProcess = (Bitmap) data.getExtras().get("data");
//                ImageView iv = (ImageView) findViewById(R.id.testImage);
//                iv.setImageBitmap(binaryOutput);

            }
            else {
                Toast.makeText(MainGUI.this, "Exited Without Taking a Photo.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void testButton(View v) {

        Intent test = new Intent(MainGUI.this, ColorBlobDetectionActivity.class);
        startActivity(test);
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
    }
}

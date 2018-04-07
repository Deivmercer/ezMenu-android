package com.tesina.davemercer.tesina;

import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.app.AlertDialog;
import android.widget.TextView;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent intent = getIntent();

    }
    protected void onResume () {
        super.onResume();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView tw = findViewById(R.id.TextView);
        tw.setText("Initializing");
        if(checkCameraHardware()) {
            builder.setMessage(R.string.dialog_camera_success).setTitle(R.string.dialog_camera_sucess_title);
            tw.setText("Camera found.");
        }
        else {
            builder.setMessage(R.string.dialog_camera_fail).setTitle(R.string.dialog_camera_fail_title);
            tw.setText("Camera not found.");
        }
        AlertDialog alert = builder.create();
        alert.show();
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware() {
        TextView tw = findViewById(R.id.TextView);
        tw.setText("Checking");
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            tw.setText("Found");
            return true;
        } else {
            // no camera on this device
            tw.setText("Not found");
            return false;
        }
    }
}

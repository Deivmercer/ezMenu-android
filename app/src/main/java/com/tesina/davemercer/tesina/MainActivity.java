package com.tesina.davemercer.tesina;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavBar();
        //setContentView(R.layout.activity_main);
        if(checkCameraHardware()) {
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_camera_fail)
                    .setTitle(R.string.dialog_camera_fail_title)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(), CodeActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    protected void onRestart(){
        super.onRestart();
        finishAndRemoveTask();
    }

    private void hideNavBar(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        }
        return false;
    }
}

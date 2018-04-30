package com.tesina.davemercer.tesina;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CameraActivity extends AppCompatActivity implements OnClickListener {

    private Button scanButton;
    private TextView scanFormat, scanContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //Intent intent = getIntent();
        scanButton = findViewById(R.id.scanButton);
        scanFormat = findViewById(R.id.scanFormat);
        scanContent = findViewById(R.id.scanContent);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.initiateScan();
        scanButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.scanButton){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            this.scanFormat.setText("FORMAT: " + scanFormat);
            this.scanContent.setText("CONTENT: " + scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //https://code.tutsplus.com/tutorials/android-sdk-create-a-barcode-reader--mobile-17162
    //https://www.spaceotechnologies.com/qr-code-android-using-zxing-library/
}

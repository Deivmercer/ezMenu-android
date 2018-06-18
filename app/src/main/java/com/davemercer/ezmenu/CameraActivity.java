package com.davemercer.ezmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CameraActivity extends AppCompatActivity implements OnClickListener {

    private Button scanButton;
    private TextView scanFormat, scanContent, test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //Intent intent = getIntent();
        scanButton = findViewById(R.id.scanButton);
        scanFormat = findViewById(R.id.scanFormat);
        scanContent = findViewById(R.id.scanContent);
        test = findViewById(R.id.test);
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
            final String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            this.scanFormat.setText("FORMAT: " + scanFormat);
            this.scanContent.setText("CONTENT: " + scanContent);
            //Integer id_d = Integer.parseInt(scanContent);
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest post = new StringRequest(Request.Method.POST,"http://ezmenu.vpsgh.it/demo/register_device.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            test.setText("Response is: "+ response);
                            File file = new File(getApplicationContext().getFilesDir(), "data.bin");
                            FileOutputStream outputStream;
                            try {
                                outputStream = openFileOutput("data.bin", getApplicationContext().MODE_PRIVATE);
                                outputStream.write(scanContent.getBytes());
                                outputStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    test.setText(error.getMessage());
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id_d", scanContent);
                    return params;
                }
            };
            queue.add(post);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //https://code.tutsplus.com/tutorials/android-sdk-create-a-barcode-reader--mobile-17162
    //https://www.spaceotechnologies.com/qr-code-android-using-zxing-library/
}

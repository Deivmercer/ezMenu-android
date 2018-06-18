package com.davemercer.ezmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button codeConfirm;
    private TextView result;
    private EditText codeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        codeConfirm = findViewById(R.id.codeConfirm);
        result = findViewById(R.id.result);
        codeInput = findViewById(R.id.codeInput);
        codeConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.codeConfirm) {
            result.setText(codeInput.getText());
        }
    }
}

package com.example.studentportal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortalActivity extends AppCompatActivity {
    private EditText labelTxt;
    private EditText URLTxt;
    private Button addPortalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        labelTxt = findViewById(R.id.editLabel);
        URLTxt = findViewById(R.id.editURL);
        addPortalButton = findViewById(R.id.addPortalButton);

        //Obtain the parameters provided by MainActivity
        final Portal portalUpdate = getIntent().getParcelableExtra(MainActivity.EXTRA_REMINDER);
        labelTxt.setText(portalUpdate.getLabel());
        URLTxt.setText(portalUpdate.getUrl());

        addPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = labelTxt.getText().toString();
                String URL = URLTxt.getText().toString();

                if (!TextUtils.isEmpty(label) && !TextUtils.isEmpty(URL)) {
                    portalUpdate.setLabel(label);
                    portalUpdate.setUrl(URL);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.EXTRA_REMINDER, portalUpdate);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();

                } else {
                    Snackbar.make(view, "Fill the form correctly", Snackbar.LENGTH_LONG);
               }
               }
        });

    }
}

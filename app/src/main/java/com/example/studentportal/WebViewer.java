package com.example.studentportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewer extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_web_view );
        webView = findViewById( R.id.webViewer );

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        //Obtain the parameters provided by MainActivity

        final Portal portalUpdate = getIntent().getParcelableExtra(MainActivity.EXTRA_REMINDER);
        webView.loadUrl(portalUpdate.getUrl());
    }
}

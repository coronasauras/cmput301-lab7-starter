package com.example.androiduitesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends Activity {
    private TextView cityNameTextView;
    private Button backButton;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        cityNameTextView = findViewById(R.id.city_name_text);
        backButton = findViewById(R.id.back_button);

        // Get city name from intent
        Intent intent = getIntent();
        cityName = intent.getStringExtra("city_name");

        if (cityName != null) {
            cityNameTextView.setText(cityName);
        }

        // Set up back button
        backButton.setOnClickListener(v -> finish());
    }

    public String getCityName() {
        return cityName;
    }

    public boolean isBackButtonVisible() {
        return backButton != null && backButton.getVisibility() == android.view.View.VISIBLE;
    }

}

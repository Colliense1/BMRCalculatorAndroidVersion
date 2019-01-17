package com.example.colliensepodder.bmrcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class BMR extends AppCompatActivity {
    RadioButton radiobuttonMale;
    RadioButton radiobuttonFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        radiobuttonMale = findViewById(R.id.radiobuttonMale);
        radiobuttonFemale = findViewById(R.id.radiobuttonFemale);

        radiobuttonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BMR.this,BasalMetabolicRate.class);
                startActivity(i);
            }
        });

        radiobuttonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BMR.this,BasalMetabolicRateFemale.class);
                startActivity(i);
            }
        });
    }
}

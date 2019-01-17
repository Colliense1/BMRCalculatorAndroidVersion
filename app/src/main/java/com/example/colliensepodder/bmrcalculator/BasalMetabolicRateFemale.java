package com.example.colliensepodder.bmrcalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BasalMetabolicRateFemale extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtTextage;
    EditText editTextHeight;
    EditText editTextInch;
    EditText edtTextWeightKg;
    LinearLayout linearlayoutCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basal_metabolic_rate_female);

        toolbar = findViewById(R.id.toolbar);
        edtTextage = findViewById(R.id.edtTextage);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextInch = findViewById(R.id.editTextInch);
        edtTextWeightKg = findViewById(R.id.edtTextWeightKg);
        linearlayoutCalculate = findViewById(R.id.linearlayoutCalculate);


        editTextHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] charSequences = new CharSequence[]
                        {AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8"};
                new AlertDialog.Builder(BasalMetabolicRateFemale.this)
                        .setTitle((CharSequence) "Select Height in Feet")
                        .setItems(charSequences, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int Cpp) {
                                BasalMetabolicRateFemale.this.editTextHeight.setText(charSequences[Cpp]);

                            }
                        }).create().show();
            }
        });

        editTextInch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] sequences = new CharSequence[]
                        {AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
                new AlertDialog.Builder(BasalMetabolicRateFemale.this)
                        .setTitle((CharSequence) "Select Height in Inches")
                        .setItems(sequences, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int Cpp) {
                                BasalMetabolicRateFemale.this.editTextInch.setText(sequences[Cpp]);
                            }
                        }).create().show();
            }
        });

        linearlayoutCalculate.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if (edtTextage.getText().toString().equals("")) {
                    edtTextage.setError("This field can't be empty");
                    return;
                } else {
                    BMRCalculator.Bmr bmr = BasalMetabolicRateFemale.BMRCalculator.getBmr
                            (BasalMetabolicRateFemale.this.edtTextage.getText().toString().trim(),
                                    BasalMetabolicRateFemale.this.editTextHeight.getText().toString().trim(),
                                    BasalMetabolicRateFemale.this.editTextInch.getText().toString().trim(),
                                    BasalMetabolicRateFemale.this.edtTextWeightKg.getText().toString().trim());
                    android.view.View inflate = getLayoutInflater().inflate(R.layout.dialog_bmr_calculation_results, null);
                    final AlertDialog create = new AlertDialog.Builder(BasalMetabolicRateFemale.this).setView(inflate).create();

                    ((TextView) inflate.findViewById(R.id.textViewBmrIndex)).setText(bmr.bmrIndex);
                    ((TextView) inflate.findViewById(R.id.textViewBmrStatus)).setText(bmr.healthStatusText);
                    TextView textView = (TextView) inflate.findViewById(R.id.textViewBmrIndex);
                    int i = bmr.healthStatusIndex;
                    int i2 = R.color.colorRed;
                    textView.setTextColor(android.support.v4.content.ContextCompat.getColor(BasalMetabolicRateFemale.this, i == 1 ?
                            R.color.green :
                            R.color.colorRed));
                    textView = (TextView) inflate.findViewById(R.id.textViewBmrStatus);
                    if (bmr.healthStatusIndex == 1) {
                        i2 = R.color.green;
                    }
                    textView.setTextColor(android.support.v4.content.ContextCompat.getColor(BasalMetabolicRateFemale.this, i2));
                    inflate.findViewById(R.id.textViewOk).setOnClickListener(new android.view.View.OnClickListener() {
                        public void onClick(android.view.View view) {
                            create.dismiss();

                        }
                    });
                    create.show();
                }
            }

        });

    }

    public static class BMRCalculator {
        public static String[] HEALTH_STATUS = new String[]{"For Female", "For Male"};

        public static class Bmr {
            public String bmrIndex;
            public int healthStatusIndex;
            public String healthStatusText;

        }

        public static Bmr getBmr(String age, String height, String feet, String kg) {
            int Age = Integer.parseInt(age);
            double height_feet = Double.parseDouble(height);
            double height_inch = Double.parseDouble(feet);
            double weight_kg = Double.parseDouble(kg);
            double height_total_inch = ((height_feet * 12) + height_inch);
            double height_in_cm = (height_total_inch * 2.54);

            double bmrfemale = ((10 * weight_kg) + (6.25 * height_in_cm) - (5 * Age) - 161);


            Bmr str23 = new Bmr();
            str23.bmrIndex = String.format("%.1f Calories/Day", new Object[]{Double.valueOf(bmrfemale)});


            str23.healthStatusText = HEALTH_STATUS[str23.healthStatusIndex];
            return str23;

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickback(android.view.View view) {
        this.finish();
    }

}

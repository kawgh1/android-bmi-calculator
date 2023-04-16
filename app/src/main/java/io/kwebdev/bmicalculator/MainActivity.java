package io.kwebdev.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private RadioButton maleButton, femaleButton;
    private EditText ageEditText, feetEditText, inchesEditText, weightEditText;
    private Button calculateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();

    }

    private void findViews() {

        resultText = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmi = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmi);
                } else {
                    displayGuidance(bmi);
                }
            }
        });
    }


    private double calculateBmi() {
        int feet = Integer.parseInt(feetEditText.getText().toString());
        int inches = Integer.parseInt(inchesEditText.getText().toString());
        int weight = Integer.parseInt(weightEditText.getText().toString());

        int totalInches = feet * 12 + inches;

        // height in meters is the inches multiplied by 0.0254
        double heightInMeters = totalInches * 0.0254;

        // BMI formula = weight in kg / heightInMeters^2
        double bmi = weight / (heightInMeters * heightInMeters);
        return bmi;

    }

    private void displayResult(double bmi) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = decimalFormat.format(bmi);


        String fullResultString;
        if (bmi < 18.5) {
            // display underweight
            fullResultString = bmiTextResult + " - You are underweight!";
        } else if (bmi > 25) {
            // display overweight
            fullResultString = bmiTextResult + " - You are overweight!";

        } else {
            // display healthy
            fullResultString = bmiTextResult + " - You are healthy weight!";

        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = decimalFormat.format(bmi);

        String fullResultString;

        if (maleButton.isChecked()) {
            // display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy BMI ranges for boys!";
        } else if (femaleButton.isChecked()) {
            // display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy BMI ranges for girls!";
        } else {
            // display general guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy BMI ranges for children!";
        }
        resultText.setText(fullResultString);
    }
}
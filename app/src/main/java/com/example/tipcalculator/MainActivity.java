package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText totalBillAmount; // for use with id: bill_value
    private SeekBar tipPercent; // for use with id: seekBar
    private TextView totalAmountToBePaid; // for use with id: total_to_pay_result
    private TextView totalAmountOfTipsToBePaid; // for use with id: total_tip_result
    private Button calculateTipAndTotal; // for use with apply button
    private int tipPercentValue = 0;
    private TextView tipPercentLabel; // for use with id: tip_percent_result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalBillAmount = (EditText) findViewById(R.id.bill_value);
        tipPercent = (SeekBar) findViewById(R.id.seekBar);

        totalAmountToBePaid = (TextView) findViewById(R.id.total_to_pay_result);
        totalAmountOfTipsToBePaid = (TextView) findViewById(R.id.total_tip_result);
        tipPercentLabel = (TextView) findViewById(R.id.tip_percent_result);

        // Display the tip percentage as user drags the seek bar horizontally
        tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipPercentValue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tipPercentLabel.setText(seekBar.getProgress() + "%");
            }
        });

        calculateTipAndTotal = (Button) findViewById(R.id.calculate_tip_and_amount);
        calculateTipAndTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double totalBillInput = Double.parseDouble(totalBillAmount.getText().toString());
                double percentageOfTip = (totalBillInput * tipPercentValue) / 100;
                double totalAmountForTheBill = totalBillInput + percentageOfTip;

                totalAmountToBePaid.setText(removeTrailingZero(String.valueOf("$" + String.format("%.2f", totalAmountForTheBill))));
                totalAmountOfTipsToBePaid.setText(removeTrailingZero(String.valueOf("$" + String.format("%.2f", percentageOfTip))));

            }
        });
    }

    public String removeTrailingZero(String formattingInput) {
        if (!formattingInput.contains(".")) {
            return formattingInput;
        }
        int dotPosition = formattingInput.indexOf(".");
        String newValue = formattingInput.substring(dotPosition, formattingInput.length());
        if (newValue.startsWith(".0")) {
            return formattingInput.substring(0, dotPosition);
        }
        return formattingInput;
    }
}
package com.example.cashpayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashpayment.preferences.AppPreference;
import com.example.cashpayment.preferences.PrefConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    TextView tvTodayAmount;
    EditText etBillAmount, etPhoneNumber;
    Button btContinue;
    ProgressBar progressBar;
    Double totalAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        tvTodayAmount = findViewById(R.id.tv_today_amount);
        etBillAmount = findViewById(R.id.et_bill_amount);
        etPhoneNumber = findViewById(R.id.et_phone_no);
        btContinue = findViewById(R.id.bt_continue);
        progressBar = findViewById(R.id.progress_bar);
        etPhoneNumber.setText("9010856400");
        Gson gson = new Gson();
        Type type = new TypeToken<Double>() {
        }.getType();
        String json = AppPreference.getInstance(MainActivity.this).getString(PrefConstants.TODAY_TOTAL_PAYMENT, "");
        totalAmount = gson.fromJson(json, type);
        if(totalAmount!=null) {
            tvTodayAmount.setText("Today's Total\n" + getString(R.string.Rs) + totalAmount);
        } else  {
            tvTodayAmount.setText("Today's Total\n" + getString(R.string.Rs) + "0.0");
        }
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBlankFields()) {
                    progressBar.setVisibility(View.VISIBLE);
                    String json = AppPreference.getInstance(MainActivity.this).getString(PrefConstants.TODAY_TOTAL_PAYMENT, "");
                    Double finalAmount;
                    if(gson.fromJson(json, type)!=null) {
                        BigDecimal todayAmount = new BigDecimal(gson.fromJson(json, type).toString());
                        BigDecimal enteredAmount = new BigDecimal(etBillAmount.getText() + "");
                        finalAmount = todayAmount.add(enteredAmount).doubleValue();
                    } else {
                        finalAmount = new BigDecimal(etBillAmount.getText() + "").doubleValue();
                    }
                    String jsonString = gson.toJson(finalAmount);
                    AppPreference.getInstance(MainActivity.this).putString(PrefConstants.TODAY_TOTAL_PAYMENT, jsonString);
                    Handler mHand  = new Handler();
                    mHand.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, PaymentConfirmActivity.class);
                            intent.putExtra("amount", etBillAmount.getText()+"");
                            progressBar.setVisibility(View.GONE);
                            startActivity(intent);
                        }
                    }, 3000);
                }
            }
        });
    }

    private boolean checkBlankFields() {
        if((etBillAmount.getText()+"").equalsIgnoreCase("")) {
            Toast.makeText(MainActivity.this, getString(R.string.please_enter_bill_amount), Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!(etPhoneNumber.getText()+"").equalsIgnoreCase("9010856400")) {
            Toast.makeText(MainActivity.this, getString(R.string.please_enter_correct_phone_number), Toast.LENGTH_SHORT).show();
            return false;
        }
        if((etPhoneNumber.getText()+"").equalsIgnoreCase("")) {
            Toast.makeText(MainActivity.this, getString(R.string.please_enter_phone_number), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
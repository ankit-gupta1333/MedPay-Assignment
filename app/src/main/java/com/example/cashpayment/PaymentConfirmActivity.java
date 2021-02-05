package com.example.cashpayment;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PaymentConfirmActivity extends AppCompatActivity {

    String billAmount;
    ImageView ivRight;
    TextView tvAmount, tvDate;
    Button btBackToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.payment_status));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        billAmount = getIntent().getExtras().getString("amount");

        tvAmount = findViewById(R.id.tv_amount);
        tvDate = findViewById(R.id.tv_date);
        ivRight = findViewById(R.id.iv_right);
        btBackToHome = findViewById(R.id.bt_back_to_home);
        ((Animatable) ivRight.getDrawable()).start();
        Double amount = new BigDecimal(billAmount).doubleValue();
        tvAmount.setText(getString(R.string.Rs)+""+amount+"");
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm aaa");
        Date date = new Date();
        tvDate.setText(formatter.format(date));

        btBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentConfirmActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}

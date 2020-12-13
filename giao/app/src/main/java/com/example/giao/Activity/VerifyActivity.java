package com.example.giao.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giao.R;

public class VerifyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
//        TextView textViewRegister = findViewById(R.id.textViewRegister);
//        textViewRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent();
//                it.setClass(VerifyActivity.this, RegisterActivity.class);
//                VerifyActivity.this.startActivity(it);
//            }
//        });
    }
}

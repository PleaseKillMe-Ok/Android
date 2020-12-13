package com.example.giao.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giao.R;

public class RegisterActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}

//public class RegisterActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        Button buttonRegister = findViewById(R.id.buttonRegister);
//        //注册按钮的点击事件
//        buttonRegister.setOnClickListener(v -> register());
//    }
//
//    private void register(){
//        EditText editTextName = findViewById(R.id.editTextName);
//        EditText editTextPassword = findViewById(R.id.editTextPassword);
//        EditText editTextPassword2 = findViewById(R.id.editTextPassword2);
//        EditText editTextPrompt = findViewById(R.id.editTextPrompt);
//
//        String name = editTextName.getText().toString();
//        if(name.length()<1) {
//            Toast.makeText(this,"昵称不能为空",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        String pwd = editTextPassword.getText().toString();
//        String pwd2 = editTextPassword2.getText().toString();
//        if (pwd.length() < 1){
//            Toast.makeText(this,"密码不能为空.",Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (!pwd.equals(pwd2)){
//            Toast.makeText(this,"两次密码不相同.",Toast.LENGTH_LONG).show();
//            return;
//        }
//        String prompt = editTextPrompt.getText().toString();
//        //用SharedPreferences方式存储数据
//        SharedPreferences sp = this.getSharedPreferences("tinyaccount", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("name",name);
//        editor.putString("password",pwd);
//        editor.putString("prompt",prompt);
//
//        editor.apply();
//        finish();
//    }
//
//}
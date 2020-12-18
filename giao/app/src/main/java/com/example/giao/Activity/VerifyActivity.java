package com.example.giao.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giao.Api.Login;
import com.example.giao.Api.SendVerification;
import com.example.giao.Bean.LoginRequest;
import com.example.giao.Bean.Phone;
import com.example.giao.Bean.Verification;
import com.example.giao.R;
import com.example.giao.utils.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private SendVerification sendVerification;
    private Phone phone;
    private LoginRequest loginRequest;
    private Login login;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_login);
        Button buttonVerify = findViewById(R.id.buttonVerify2);
        buttonLogin = findViewById(R.id.buttonLogin2);

        retrofit = Retrofit.getRetrofit();
        sendVerification = retrofit.getVerification();
        login = retrofit.getLoginResult();

        buttonVerify.setOnClickListener(new View.OnClickListener() {    //发送验证码按钮点击事件
            @Override
            public void onClick(View v) {
                //String phone = editTextPhone.getText().toString();
                SendVerifyRequest();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {      //登录按钮点击事件
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void SendVerifyRequest(){
        EditText editTextPhone = findViewById(R.id.etPhone2);
        String phoneNumber = editTextPhone.getText().toString().trim();
        if(phoneNumber.length()<1) {
            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        boolean judge = isPhoneNumberLegal(phoneNumber);
        if (judge == true) {
            //Toast.makeText(this,"手机号合法",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"手机号不合法",Toast.LENGTH_LONG).show();
        }
        phone = new Phone();
        phone.setPhone(phoneNumber);
        System.out.println(phone.getPhone());


        Call<Verification> call = sendVerification.getVerification(phone);
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                //response.code()
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    Verification result = response.body();//关键
                    if (result != null) {
                        Toast.makeText(VerifyActivity.this,"已发送验证码！请注意查收！",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                System.out.println("请求失败");
            }
        });
    }

    private void Login(){
        EditText editTextPhone = findViewById(R.id.etPhone2);
        EditText editTextVerification = findViewById(R.id.editTextVerify2);
        buttonLogin = findViewById(R.id.buttonLogin2);

        String phone = editTextPhone.getText().toString().trim();
        String verification = editTextVerification.getText().toString().trim();
        int verifyLength = verification.length();
        System.out.println(phone);
        System.out.println(verification);

        loginRequest = new LoginRequest();
        loginRequest.setPhone(phone);
        loginRequest.setWay("code");
        loginRequest.setPassword(verification);

        if(phone.length() < 1) {
            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(verifyLength<1) {
            Toast.makeText(this,"验证码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(verifyLength>0 && verifyLength!=5 ) {
            Toast.makeText(this,"验证码格式不正确",Toast.LENGTH_LONG).show();
            return;
        }


        Call<Verification> call = login.getLoginResult(loginRequest);      //发送异步请求
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {      ///** Returns true if {@link #code()} is in the range [200..300)
                    Verification result = response.body();//关键
                    if (result != null) {
                        Toast.makeText(VerifyActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
                    }
                }
                if(response.code()==400){
                    Toast.makeText(VerifyActivity.this,"验证码错误！",Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                System.out.println("请求失败");
                Toast.makeText(VerifyActivity.this,"登录失败！",Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 验证手机格式
     */
    public static boolean isPhoneNumberLegal(String phoneNumber) {
    /*
    移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、178(新)、182、184、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、170、173、177、180、181、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String reg = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return phoneNumber.matches(reg);
        }
    }
}

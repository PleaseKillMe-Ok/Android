package com.example.giao.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giao.Api.Register;
import com.example.giao.Api.SendVerification;
import com.example.giao.Bean.Information;
import com.example.giao.Bean.Phone;
import com.example.giao.Bean.RegisterRequest;
import com.example.giao.Bean.Verification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

import com.example.giao.R;
import com.example.giao.utils.Retrofit;

public class RegisterActivity extends AppCompatActivity{
    private Retrofit retrofit;
    private SendVerification sendVerification;
    private Phone phone;
    private RegisterRequest registerRequest;
    private Register register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonVerify = findViewById(R.id.buttonVerify);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        //EditText editTextPhone = findViewById(R.id.editTextPhone);

        retrofit = Retrofit.getRetrofit();
        sendVerification = retrofit.getVerification();
        register = retrofit.getRegisterResult();

        buttonVerify.setOnClickListener(new View.OnClickListener() {    //发送验证码按钮点击事件
            @Override
            public void onClick(View v) {
                //String phone = editTextPhone.getText().toString();
                SendVerifyRequest();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {  //注册按钮点击事件
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextPassword2 = findViewById(R.id.editTextPassword2);
        EditText editTextVerify = findViewById(R.id.editTextVerify);

        String phoneNumber = editTextPhone.getText().toString().trim();
        String password1 = editTextPassword.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();
        String verifyCode = editTextVerify.getText().toString().trim();
        int verifyLength = verifyCode.length();
        System.out.println(phoneNumber);
        System.out.println(password1);
        System.out.println(password2);
        System.out.println(verifyCode);

        registerRequest = new RegisterRequest();
        registerRequest.setPhone(phoneNumber);
        registerRequest.setPassword(password1);
        registerRequest.setCode(verifyCode);

        if(phoneNumber.length()<1) {
            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if (password1.length() < 1){
            Toast.makeText(this,"密码不能为空.",Toast.LENGTH_LONG).show();
            return;
        }
        if (!password2.equals(password1)){
            Toast.makeText(this,"两次密码不相同.",Toast.LENGTH_LONG).show();
            return;
        }
        if(verifyLength<1) {
            Toast.makeText(this,"验证码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(verifyLength>0 && verifyLength!=5) {
            Toast.makeText(this,"验证码格式不正确",Toast.LENGTH_LONG).show();
            return;
        }


        Call<Verification> call = register.getRegisterResult(registerRequest);      //发送异步请求
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {      ///** Returns true if {@link #code()} is in the range [200..300)
                    Verification result = response.body();//关键
                    if (result != null) {
                        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
                    }
                }
                if(response.code()==400){
                    Toast.makeText(RegisterActivity.this,"验证码不正确！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(response.code()==500){
                    Toast.makeText(RegisterActivity.this,"用户已经存在！",Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                System.out.println("请求失败");
            }
        });
    }

    private void SendVerifyRequest(){
        EditText editTextPhone = findViewById(R.id.editTextPhone);
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
        //Information info = new Information();
        //info.setPhone(phoneNumber);
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
//                        int code = result.getCode();
//                        String status = result.getStatus();
                        Toast.makeText(RegisterActivity.this,"已发送验证码！请注意查收！",Toast.LENGTH_LONG).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                System.out.println("请求失败");
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
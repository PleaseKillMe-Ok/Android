package com.example.giao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giao.Api.User;
import com.example.giao.Bean.Information;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private Button mButton;
    private User user;
    private EditText editTextName;
    private TextView textViewIntegralValue;
    private TextView textViewPhoneValue;
    private TextView textViewMemberValue;
    private ImageView headImage;

    // 个人信息操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        headImage = findViewById(R.id.imageViewHeadImage);  //获取头像控件
        editTextName = findViewById(R.id.editTextName); // 获取用户名控件
        textViewIntegralValue = findViewById(R.id.textViewIntegralValue); // 获取积分控件
        textViewPhoneValue = findViewById(R.id.textViewPhoneValue); // 获取手机号控件
        textViewMemberValue = findViewById(R.id.textViewMemberValue);  //获取是否是会员控件


        retrofit = Retrofit.getRetrofit();
        user = retrofit.getUserInfo();
        mButton = findViewById(R.id.buttonInfoSave);
    }

    public void saveInfo(View view) {
        // 获取个人信息
        Call<Information> call = user.getInformation();  // 回调该方法,发送请求
        call.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Call<Information> call, Response<Information> response) {
                if (response.isSuccessful()) {
                    Information result = response.body();  //获取响应数据，转为实体类
                    if (result != null) {

                        String headImage = result.getHead_image();
                        String phone = result.getPhone();
                        String username = result.getUsername();
                        Boolean is_member = result.getIs_member();
                        Integer integral = result.getIntegral();
                        String strIsMemeber;

                        if (is_member){
                            strIsMemeber = "是";
                        }else {
                            strIsMemeber = "否";
                        }
                        editTextName.setText(username);
                        textViewIntegralValue.setText(integral);
                        textViewPhoneValue.setText(phone);
                        textViewMemberValue.setText(strIsMemeber);


                        System.out.println("请求成功");
                    }
                }
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {
                System.out.println("请求失败");
            }
        });


    }
}

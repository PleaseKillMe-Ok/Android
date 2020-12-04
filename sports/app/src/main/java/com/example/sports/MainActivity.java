package com.example.sports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sports.Retrofit.getRetrofit;


public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private Retrofit retrofit;
    private GetRequest_Interface service;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mButton = (Button)findViewById(R.id.button);
        retrofit = getRetrofit();
        mTextView = (TextView) findViewById(R.id.tv_2);
        service = retrofit.getService();

    }

    public void ttt(View view) {
        Call<Bean> call = service.getMenuById();
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                if (response.isSuccessful()) {
                    Bean result = response.body();//关键
                    if (result != null) {

                        int counts = result.getAll_notes_counts();
                        int praise = result.getAll_notes_praise();
                        int visit = result.getAll_notes_visit();
                        mTextView.setText("counts"+counts+"praise"+praise+"visit"+visit);
                        System.out.println("23WWWWWWWW1232131");
                    }
                }
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                System.out.println("21312312");
            }
        });

    }
}

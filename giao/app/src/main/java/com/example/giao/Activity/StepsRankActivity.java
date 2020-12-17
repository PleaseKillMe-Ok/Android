package com.example.giao.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giao.Adapter.UserRankAdapter;
import com.example.giao.Api.User;
import com.example.giao.Bean.UserRank;
import com.example.giao.R;

import java.util.ArrayList;
import java.util.List;

public class StepsRankActivity extends AppCompatActivity {
    // fruitList用于存储数据
    private List<UserRank> userRankList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_steps_rank);

        // 先拿到数据并放在适配器上
        initUserRankList(); //初始化水果数据
        UserRankAdapter adapter=new UserRankAdapter(StepsRankActivity.this,R.layout.user_steps_item,userRankList);

        // 将适配器上的数据传递给listView
        ListView listView=findViewById(R.id.user_steps_list_view);
        listView.setAdapter(adapter);

        // 为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick()方法
        // 在这个方法中可以通过position参数判断出用户点击的是那一个子项
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserRank userRank=userRankList.get(position);
                Toast.makeText(StepsRankActivity.this,userRank.getUsername(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 初始化数据
    private void initUserRankList(){
//        for(int i=0;i<10;i++){
//            UserRank a=new UserRank("a",R.drawable.a);
//            userRankList.add(a);
//            UserRank b=new UserRank("B",R.drawable.b);
//            userRankList.add(b);
//            UserRank c=new UserRank("C",R.drawable.c);
//            userRankList.add(c);
//            UserRank d=new UserRank("D",R.drawable.d);
//            userRankList.add(d);
//        }
    }
}


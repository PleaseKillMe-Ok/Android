package com.example.giao.Activity;

import android.os.Bundle;

import com.example.giao.R;
import com.example.giao.fragment.CountStepsFragment;
import com.example.giao.fragment.StepRankFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class BottomMenuActivity extends AppCompatActivity{
    private TextView mTextMessage;
    private CountStepsFragment count_steps_fragment;
    private StepRankFragment rank_steps_fragment;
    private FragmentTransaction transaction;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);  // navView控件
        mTextMessage = findViewById(R.id.message);  //测试移动
        manager = getSupportFragmentManager();//获取FragmentManage的方式
        transaction = manager.beginTransaction();//开启一个事务
        // 设定navView的选择监听器
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 默认选中第一个
        count_steps_fragment = new CountStepsFragment();
        transaction.replace(R.id.fragment_content, count_steps_fragment);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hideFragment(transaction); // 清除旧的fragment
                    count_steps_fragment = new CountStepsFragment();
                    // 一次commit对于一个transaction只能提交一次，因此需要new一个新的transaction
                    transaction.replace(R.id.fragment_content, count_steps_fragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    hideFragment(transaction); // 清除旧的fragment
                    mTextMessage.setText(R.string.title_dashboard);
                    rank_steps_fragment = new StepRankFragment();
                    transaction.replace(R.id.fragment_content, rank_steps_fragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    hideFragment(transaction); // 清除旧的
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };



    /*
     * 去除（隐藏）所有的Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (count_steps_fragment != null) {
            //transaction.hide(f1);  隐藏方法也可以实现同样的效果，不过我一般使用去除
           transaction.remove(count_steps_fragment);
        }
        if (rank_steps_fragment != null) {
            //transaction.hide(f2);
          transaction.remove(rank_steps_fragment);
        }

    }


}

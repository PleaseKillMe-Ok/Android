package com.example.giao.fragment;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.giao.Activity.CountStepsActivity;
import com.example.giao.R;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.SENSOR_SERVICE;

// Fragment 片段，设置初始内容
public class CountStepsFragment extends Fragment implements View.OnClickListener, SensorEventListener {
    private SensorManager sManager;
    private Sensor mSensorAccelerometer;
    private TextView tv_step;
    private Button btn_start;
    private int step = 0;   //步数
    private double oriValue = 0;  //原始值
    private double lstValue = 0;  //上次的值
    private double curValue = 0;  //当前值
    private boolean motiveState = true;   //是否处于运动状态
    private boolean processState = false;   //标记当前是否已经在计步
    private FragmentActivity activity;
    Timer timer;
    TimerTask task;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_steps, container, false);

        activity= getActivity();
        sManager = (SensorManager) activity.getSystemService(SENSOR_SERVICE);
        mSensorAccelerometer = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this, mSensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        bindViews(view);

//        btn_start = view.findViewById(R.id.btn_start);

        timer=new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
//                System.out.println(step);
//                tv_step.setText(step+"");
                //System.out.println("aoligei");
                //Message message = new Message();
                //message.what = 1;
                //handler.sendMessage(message);
            }
        };
        timer.schedule(task, 5000,5000);


        //对视图中的控件进行初始化
        return view;
    }

    @CallSuper
    public void onDestroyView() {


        super.onDestroyView();
        timer.cancel();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        btn_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                step = 0;
                tv_step.setText("0");
                if (processState == true) {
                    btn_start.setText("开始");
                    processState = false;
                } else {
                    btn_start.setText("停止");
                    processState = true;
                }
            }
        });
    }


    private void bindViews(View view) {

        tv_step = view.findViewById(R.id.tv_step);
        btn_start = view.findViewById(R.id.btn_start);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        double range = 1;   //设定一个精度范围
        float[] value = event.values;
        curValue = magnitude(value[0], value[1], value[2]);   //计算当前的模
        //向上加速的状态
        if (motiveState == true) {
            if (curValue >= lstValue) lstValue = curValue;
            else {
                //检测到一次峰值
                if (Math.abs(curValue - lstValue) > range) {
                    oriValue = curValue;
                    motiveState = false;
                }
            }
        }
        //向下加速的状态
        if (motiveState == false) {
            if (curValue <= lstValue) lstValue = curValue;
            else {
                if (Math.abs(curValue - lstValue) > range) {
                    //检测到一次峰值
                    oriValue = curValue;
                    if (processState == true) {
                        step++;  //步数 + 1
                        if (processState == true) {
                            tv_step.setText(step + "");    //读数更新
                        }
                    }
                    motiveState = true;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onClick(View view) {
        step = 0;
        tv_step.setText("0");
        if (processState == true) {
            btn_start.setText("开始");
            processState = false;
        } else {
            btn_start.setText("停止");
            processState = true;
        }
    }

    //向量求模
    public double magnitude(float x, float y, float z) {
        double magnitude = 0;
        magnitude = Math.sqrt(x * x + y * y + z * z);
        return magnitude;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sManager.unregisterListener(this);
    }
}

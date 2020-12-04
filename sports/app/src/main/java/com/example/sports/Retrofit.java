package com.example.sports;



import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.id.textView1);
//
//        request();
//        // 使用Retrofit封装的方法
//    }
//    public void request() {
//
//        //步骤4:创建Retrofit对象
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
//                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
//                .build();
//
//        // 步骤5:创建 网络请求接口 的实例
//        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
//
//        //对 发送请求 进行封装
//        Call<Bean> call = request.getCall();
//
//        //步骤6:发送网络请求(异步)
//        call.enqueue(new Callback<Bean>() {
//            //请求成功时回调
//            @Override
//            public void onResponse(Call<Bean> call, Response<Bean> response) {
//                // 步骤7：处理返回的数据结果
//                response.body().show();
//            }
//
//            //请求失败时回调
//            @Override
//            public void onFailure(Call<Bean> call, Throwable throwable) {
//                System.out.println("连接失败");
//            }
//        });
//    }

    private GetRequest_Interface service;

    /**
     * 获取Retrofit实例
     * @return
     */
    public static Retrofit getRetrofit(){
        return new Retrofit();
    }

    private Retrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://syzzjw.cn/")
//                .baseUrl("http://www.tngou.net/api/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GetRequest_Interface.class);
    }

    /**
     * 获取IBeanService实例
     * @return
     */
    public GetRequest_Interface getService(){
        return service;
    }
}


package com.example.giao.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "{\n    \"phone\":\"13787830\"\n}");
    Request request = new Request.Builder()
            .url("http://ij8cy3yo.shenzhuo.vip:11601/auth-api/code-api")
            .method("POST", body)
            .build();

    Response response = client.newCall(request).execute();

    public HttpRequest() throws IOException {
        System.out.println("!!!!!!");
    }
}

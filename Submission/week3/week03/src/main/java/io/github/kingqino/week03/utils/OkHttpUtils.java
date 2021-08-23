package io.github.kingqino.week03.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;


public class OkHttpUtils {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    final static OkHttpClient client = new OkHttpClient();

    public static Response getCall(String url) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();

        return doRequest(request);
    }

    public static Response postCall(String url, String json) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .post(body)
                .build();

        return doRequest(request);
    }

    private static Response doRequest(Request request) {
        Response result = null;
        try {
            result = client.newCall(request).execute();
//            result = response.code() + "\n" +  Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) {
        Response response = getCall("http://localhost:8801");

        Response response_ = postCall("http://localhost:8801", "{name: 'MIKE'}");

        System.out.println(response);
        System.out.println(response_);
    }
}

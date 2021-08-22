package io.github.kingqino.week03.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtils {

    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static void main(String[] args) {
        String url = "http://localhost:8801";

        System.out.println(getAsString(url));

        Map<String, String> headers = new HashMap<>();
        System.out.println(postAsJson(url, "", headers));
    }

    private static String postAsJson(String url, String json, Map<String, String> headers) {
        String result = null;

        HttpPost httpPost = new HttpPost(url);
        for (String name : headers.keySet()) {
            httpPost.setHeader(name, headers.get(name));
        }

        final String JSON_TYPE = "application/json;charset=UTF-8";
        httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_TYPE);
        StringEntity requestEntity = new StringEntity(json, StandardCharsets.UTF_8);
        httpPost.setEntity(requestEntity);

        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            result = response.getStatusLine() + "\n" + EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getAsString(String url) {
        String result = null;

        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            result = response.getStatusLine() + "\n" + EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}

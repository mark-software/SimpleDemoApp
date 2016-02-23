package com.demo.markymark.simpledemoapp.helpers;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mark.miller on 2/23/16.
 */
public class NetworkHelper {

    private static OkHttpClient client;

    static {
        if (client == null)
            client = new OkHttpClient();
    }

    public static String getRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return null;
        }
    }
}

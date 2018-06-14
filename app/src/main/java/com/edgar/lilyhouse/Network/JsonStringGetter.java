package com.edgar.lilyhouse.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class JsonStringGetter {

    public void loadJsonString(String queryUrl) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        URL url = null;
        try {
            url = new URL(queryUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            String jsonString = builder.toString();
            onLoadSucceed(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
            onLoadFailed(101);

        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            onLoadFailed(102);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    onLoadFailed(103);
                }
            }
        }
    }

    public abstract void onLoadFailed(int resultCode);

    public abstract void onLoadSucceed(String jsonString);
}

package bintang.id.restfulexample;

import android.support.annotation.NonNull;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RestClient {


    @NonNull
    public static String getContent(HttpResponse httpResponse) throws IOException {
        String result;
        InputStream inputStream = httpResponse.getEntity().getContent();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();

        String bufferedStrChunk;
        while ((bufferedStrChunk = reader.readLine()) != null) {
            sb.append(bufferedStrChunk);
        }
        result = sb.toString();
        return result;
    }

    public HttpResponse get(String url) throws Exception {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        return httpClient.execute(httpGet);
    }

    public HttpResponse put(String url, String auth, String json) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(url);
        setHeader(auth, httpPut);

        StringEntity entity= new StringEntity(json, HTTP.UTF_8);
        //entity.setContentType("text/json");
        httpPut.setEntity(entity);

        return httpClient.execute(httpPut);
    }

    private void setHeader(String auth, HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("Authorization", auth);
        httpRequestBase.setHeader("Content-Type", "application/json");
    }

    public HttpResponse delete(String url, String auth) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        setHeader(auth, httpDelete);

        return httpClient.execute(httpDelete);
    }

    public HttpResponse post(String url, String auth, String json) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        setHeader(auth, httpPost);
        StringEntity entity = new StringEntity(json, HTTP.UTF_8);
        //entity.setContentType("text/json");
        httpPost.setEntity(entity);

        HttpResponse httpResponse;
        httpResponse = httpClient.execute(httpPost);
        return httpResponse;
    }
}

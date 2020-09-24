package com.exercise.util;

import com.exercise.pojo.UrlParams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    public static String doGet(UrlParams params) {
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpGet get = new HttpGet(params.getUrl());

                get.setHeader("cookie",params.getCookie());
                get.setHeader("referer",params.getReferer());

                httpresponse = httpclient.execute(get);
                String response = EntityUtils.toString(httpresponse.getEntity());
                return  response;
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

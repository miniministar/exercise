package com.exercise.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    public static String doGet(String url) {
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);

                get.setHeader("cookie","__mgjuuid=3e482381-6a92-4853-a140-76c06279731a; FRMS_FINGERPRINTN=_51veHI3TjmsSvMSjwKUVw; _mwp_h5_token_enc=8f5d43120a00c306538ad4268ad5223f; _mwp_h5_token=1f2d8a7b83f0d8ee8b8ee827f5bdfba4_1600610853132; _ga=GA1.2.1520040884.1600612476; _gid=GA1.2.1626987880.1600612476");
                get.setHeader("referer","https://act.mogu.com/imc/ssale/20200919/branch/clothes?ptp=32._mf1_1239_4537.0.0.Yo9rHbxP&acm=3.mce.1_10_1poec.140653.0.bbTeVsb8A9SMi.pos_1-m_598982-sd_119-mf_4537_1211938-idx_1-mfs_15-dm1_5000");

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

package test.yun.com;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.region.Region;

import java.net.URL;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        GenerateSimplePresignedDownloadUrl();
    }

    // 获取下载的预签名连接
    public static void GenerateSimplePresignedDownloadUrl() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDQowrgHdvwjGWWHCsJVR8h1575qPjndEW", "JQHtJWUEj04uY0GHCtMcfUGo7A2h8NQI");
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 如果要获取 https 的 url 则在此设置，否则默认获取的是 http url
//        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        String bucketName = "video-1257599456";

        String key = "data/video/新猫和老鼠 第一季01_SD.m3u8";
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置则默认使用ClientConfig中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 120 * 60 * 1000);
        req.setExpiration(expirationDate);

        // 填写本次请求的参数
        req.addRequestParameter("ci-process", "pm3u8");
        req.addRequestParameter("expires", "3600");

        // 填写本次请求的头部。Host 头部会自动补全，不需要填写
//        req.putCustomRequestHeader("header1", "value1");

        URL url = cosclient.generatePresignedUrl(req);
        System.out.println(url.toString());

        cosclient.shutdown();
    }
}

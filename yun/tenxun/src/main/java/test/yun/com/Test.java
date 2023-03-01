package test.yun.com;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ciModel.snapshot.PrivateM3U8Request;
import com.qcloud.cos.model.ciModel.snapshot.PrivateM3U8Response;
import com.qcloud.cos.region.Region;

import java.net.URL;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        GenerateSimplePresignedDownloadUrl();
        GenerateSimplePresignedDownloadUrl2();
    }

    private static void GenerateSimplePresignedDownloadUrl2() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("1", "2");
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 如果要获取 https 的 url 则在此设置，否则默认获取的是 http url
//        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        //1.创建截图请求对象
        PrivateM3U8Request request = new PrivateM3U8Request();
        //2.添加请求参数 参数详情请见api接口文档
        request.setBucketName("base-file-mgt-1302005629");
        //私有 ts 资源 url 下载凭证的相对有效期，单位为秒，范围为[3600, 43200]
        request.setExpires("3600");
        String key = "dom_files/dev/video/2023/02/10/6-6_Linux的进程管理.m3u8";

        request.setObject(key);
        //3.调用接口,获取截图响应对象
        PrivateM3U8Response privateM3U8 = cosclient.getPrivateM3U8(request);
        System.out.println(privateM3U8.getM3u8());
        System.out.println(privateM3U8.getRequestId());
    }

    // 获取下载的预签名连接
    public static void GenerateSimplePresignedDownloadUrl() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("1", "2");
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 如果要获取 https 的 url 则在此设置，否则默认获取的是 http url
//        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        String bucketName = "base-file-mgt-1302005629";

        String key = "dom_files/dev/video/2023/02/10/6-6_Linux的进程管理.m3u8";
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

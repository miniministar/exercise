package test.yun.com;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Date;
import java.util.TreeMap;

@RequestMapping("/api")
@RestController
@SpringBootApplication
public class CosTest {

    public static void main(String[] args) {
        SpringApplication.run(CosTest.class, args);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello cors";
    }

    @RequestMapping("/sign")
    public String sign(String url){

        long startTimestamp = Instant.now().getEpochSecond();
        long endTimestamp = startTimestamp + 3600;

//        System.out.println(unixTime);
        System.out.printf(url);
        URLEncoder.encode(url);
        String sha1HttpString = DigestUtils.sha1Hex(url);
        String signKey = HmacUtils.hmacSha1Hex("YourSecretKey", "ExampleKeyTime");
        System.out.println(sha1HttpString);
        System.out.println(signKey);
        return "sing";
    }
    /**
     * 基本的临时密钥申请示例，适合对一个桶内的一批对象路径，统一授予一批操作权限
     */
    @GetMapping("sts")
    public Response testGetCredential() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();

        try {
            // 云 api 密钥 SecretId
            config.put("secretId", "AKIDQowrgHdvwjGWWHCsJVR8h1575qPjndEW");
            // 云 api 密钥 SecretKey
            config.put("secretKey", "JQHtJWUEj04uY0GHCtMcfUGo7A2h8NQI");

            // 设置域名,可通过此方式设置内网域名
            //config.put("host", "sts.internal.tencentcloudapi.com");

            // 临时密钥有效时长，单位是秒
            config.put("durationSeconds", 1800);

            // 换成你的 bucket
            config.put("bucket", "video-1257599456");
            // 换成 bucket 所在地区
            config.put("region", "ap-beijing");

            // 可以通过 allowPrefixes 指定前缀数组, 例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
            config.put("allowPrefixes", new String[] {
                    "data/video/*",
            });

            // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    "name/cos:PostObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            Response response = CosStsClient.getCredential(config);
            System.out.println(JSON.toJSONString(response));
            System.out.println(response.credentials.tmpSecretId);
            System.out.println(response.credentials.tmpSecretKey);
            System.out.println(response.credentials.sessionToken);
            System.out.println("startTime: "+ response.startTime);
            System.out.println("expiredTime: " + response.expiredTime);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }
    }

}

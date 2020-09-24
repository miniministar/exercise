package com.exercise.controller;

import com.alibaba.fastjson.JSON;
import com.exercise.pojo.QueryVo;
import com.exercise.pojo.UrlParams;
import com.exercise.util.HttpClientUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mogu/")
@Slf4j
public class MoguController {

    @PostMapping("searchActionLet")
    @ApiOperation(value = "", notes = "{\"activityLaunchId\":\"95\",\"pageSize\":18,\"appPlat\":\"m\",\"code\":\"entranceMore\"}")
    public String searchActionLet() {
        UrlParams urlParams = new UrlParams();
        urlParams.setCookie("__mgjuuid=3e482381-6a92-4853-a140-76c06279731a; FRMS_FINGERPRINTN=_51veHI3TjmsSvMSjwKUVw; _mwp_h5_token_enc=8f5d43120a00c306538ad4268ad5223f; _mwp_h5_token=1f2d8a7b83f0d8ee8b8ee827f5bdfba4_1600610853132; _ga=GA1.2.1520040884.1600612476; _gid=GA1.2.1626987880.1600612476");
        urlParams.setReferer("https://act.mogu.com/imc/ssale/20200919/branch/clothes?ptp=32._mf1_1239_4537.0.0.Yo9rHbxP&acm=3.mce.1_10_1poec.140653.0.bbTeVsb8A9SMi.pos_1-m_598982-sd_119-mf_4537_1211938-idx_1-mfs_15-dm1_5000");
        String url = "https://api.mogu.com/h5/mwp.ferrari.searchActionLet/1/";
        Map<String, Object> data = new HashMap<>();
        data.put("activityLaunchId", "95");
        data.put("pageSize", 18);
        data.put("appPlat", "m");
        data.put("code", "entranceMore");
        url += "?mw-appkey=100028&mw-ttid=NMMain%40mgj_h5_1.0&mw-t=1600616724186&mw-uuid=3e482381-6a92-4853-a140-76c06279731a&mw-h5-os=iOS&mw-sign=16fee54df1d9945bb5fb09ae8833c859&callback=mwpCb1&_=1600616724190";
//        url+= "?data=" + URLEncoder.encode(JSON.toJSONString(data));
//        url+= "&data=" + "%7B%22activityLaunchId%22%3A%2295%22%2C%22pageSize%22%3A18%2C%22appPlat%22%3A%22m%22%2C%22code%22%3A%22entranceMore%22%7D";
        url+= "&data=" + URLEncoder.encode("{\"activityLaunchId\":\"95\",\"pageSize\":18,\"appPlat\":\"m\",\"code\":\"entranceMore\"}");
        urlParams.setUrl(url);

        log.info("url: {}", urlParams);
        String reponse = HttpClientUtil.doGet(urlParams);
        return  reponse;
    }

    @PostMapping("paganiSearch")
    @ApiOperation(value = "", notes = " {\"page\":1,\"pageSize\":20,\"isGoBackup\":true,\"cKey\":\"app-clothing-v1\",\"fcid\":\"20000484\"}")
    //data: {"page":2,"pageSize":20,"isGoBackup":true,"cKey":"app-clothing-v1","fcid":"20000484"}
    public String paganiSearch(@RequestBody QueryVo vo) {
        UrlParams urlParams = new UrlParams();
        urlParams.setCookie("__mgjuuid=3e482381-6a92-4853-a140-76c06279731a; FRMS_FINGERPRINTN=_51veHI3TjmsSvMSjwKUVw; _mwp_h5_token_enc=8f5d43120a00c306538ad4268ad5223f; _mwp_h5_token=1f2d8a7b83f0d8ee8b8ee827f5bdfba4_1600610853132; _ga=GA1.2.1520040884.1600612476; _gid=GA1.2.1626987880.1600612476");
        urlParams.setReferer("https://act.mogu.com/imc/ssale/20200919/branch/clothes?ptp=32._mf1_1239_4537.0.0.oSZROPdf&acm=3.mce.1_10_1poec.140653.0.yDrx6sbecUmFe.pos_1-m_598982-sd_119-mf_4537_1211938-idx_1-mfs_15-dm1_5000");
        String url = "https://api.mogu.com/h5/mwp.pagani.search/21/";
//        url+= "?data=" + URLEncoder.encode(JSON.toJSONString(vo));
        url+= "?data=" + URLEncoder.encode("{\"page\":"+vo.getPage()+",\"pageSize\":"+vo.getPageSize()+",\"isGoBackup\":"+vo.getIsGoBackup()+",\"cKey\":\""+vo.getCKey()+"\",\"fcid\":\""+vo.getFcid()+"\"}");
        url+= "&mw-appkey=100028&mw-ttid=NMMain%40mgj_h5_1.0&mw-t=1600694005075&mw-uuid=3e482381-6a92-4853-a140-76c06279731a&mw-h5-os=iOS&mw-sign=36a6d78ea4b033e6a81e83bbdfb1f109&callback=mwpCb2&_=1600694005079";
        urlParams.setUrl(url);

        log.info("url: {}", urlParams);
        String reponse = HttpClientUtil.doGet(urlParams);
        return  reponse;
    }

    @PostMapping("listSearch")
    @ApiOperation(value = "", notes = " {\"page\":1,\"pageSize\":20,\"isGoBackup\":true,\"cKey\":\"app-clothing-v1\",\"fcid\":\"20000484\"}")
    public String listSearch(@RequestBody QueryVo vo) {
        UrlParams urlParams = new UrlParams();
        urlParams.setCookie("__mgjuuid=3e482381-6a92-4853-a140-76c06279731a; FRMS_FINGERPRINTN=_51veHI3TjmsSvMSjwKUVw; _mwp_h5_token_enc=8f5d43120a00c306538ad4268ad5223f; _mwp_h5_token=1f2d8a7b83f0d8ee8b8ee827f5bdfba4_1600610853132; _ga=GA1.2.1520040884.1600612476; __mgjref=https%3A%2F%2Fwebim.mogu.com%2Fh5%3Fptp%3D32._mf1_1239_70922.0.0.n3wjNsc6%26acm%3D3.mf.1_0_0.0.0.0.mf_70922_1043091; _TDeParam=1-sp4A2FKYDWOdKiyV9RINKg");
        urlParams.setReferer("https://m.mogu.com/wall/s?q=%E5%A5%B3%E8%A3%85&ptp=32._mf1_1239_4537.0.0.0gB0BHOW&acm=3.mce.1_10_1prjy.140653.0.gGQ8fsbk55526.pos_873-m_601027-sd_119-mf_4537_1211938-idx_1-mfs_15-dm1_5000");
        String url = "https://list.mogu.com/search?_version=8253&ratio=3%3A4&cKey=46&sort="+vo.getSort()+"&page="+vo.getPage()+"&q=%25E5%25A5%25B3%25E8%25A3%2585&minPrice=&maxPrice=&ppath=&cpc_offset=&ptp=32._mf1_1239_4537.0.0.0gB0BHOW&acm=3.mce.1_10_1prjy.140653.0.gGQ8fsbk55526.pos_873-m_601027-sd_119-mf_4537_1211938-idx_1-mfs_15-dm1_5000&offset="+(vo.getPageSize() * (vo.getPage()-1))+"&_=1600781634877&callback=jsonp2";
        urlParams.setUrl(url);
        log.info("url: {}", urlParams);
        String reponse = HttpClientUtil.doGet(urlParams);
        return  reponse;
    }
}

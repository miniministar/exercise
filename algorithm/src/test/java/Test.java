import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void test() {
        String a = "";
        JSONObject jsonObject = JSONObject.parseObject(a);
        JSONArray leftChildren = jsonObject.getJSONArray("leftChildren");
        JSONArray children = jsonObject.getJSONArray("children");

        boolean b = leftChildren.addAll(children);
        JSONArray secondArray = new JSONArray();
        for (Object leftChild : leftChildren) {
            Map<String, Object> leftChild1 = (Map<String, Object>) leftChild;
            secondArray.addAll((JSONArray) leftChild1.get("children"));
        }

        JSONArray thirdArray = new JSONArray();
        for (Object o : secondArray) {
            Map<String, Object> o1 = (Map<String, Object>) o;
            thirdArray.addAll((JSONArray) o1.get("children"));
        }

        JSONArray forthArray = new JSONArray();
        for (Object o : thirdArray) {
            Map<String, Object> o1 = (Map<String, Object>) o;
            forthArray.addAll((JSONArray) o1.get("children"));
        }
        JSONArray fifthArray = new JSONArray();
        for (Object o : forthArray) {
            Map<String, Object> o1 = (Map<String, Object>) o;
            fifthArray.addAll((JSONArray) o1.get("children"));
        }
        JSONArray sixArray = new JSONArray();
        for (Object o : fifthArray) {
            Map<String, Object> o1 = (Map<String, Object>) o;
            sixArray.addAll((JSONArray) o1.get("children"));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", leftChildren);
        String s = JSON.toJSONString(map);
        String s1 = JSON.toJSONString(map);
        map.put("1", secondArray);
        String s2 = JSON.toJSONString(map);
        map.put("1", thirdArray);
        String s3 = JSON.toJSONString(map);
        map.put("1", forthArray);
        String s4 = JSON.toJSONString(map);
        map.put("1", fifthArray);
        String s5 = JSON.toJSONString(map);
        map.put("1", sixArray);
        String s6 = JSON.toJSONString(map);
        System.out.printf("" + s);
        System.out.printf("" + s1);
        System.out.printf("" + s2);
        System.out.printf("" + s3);
        System.out.printf("" + s4);
        System.out.printf("" + s5);
        System.out.printf("" + s6);
    }
}

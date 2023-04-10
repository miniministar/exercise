package mongodb.springboot;


import mongodb.springboot.entity.Cast;
import mongodb.springboot.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert() {
        User user1 = User.builder()
                .name("张三")
                .email1("1234@qq.com")
                .password("password")
                .build();

        mongoTemplate.insert(user1);
    }

    @Test
    public void jsonSearch() {
//        String query = "[{'$unwind':'$cast'},{'$group':{'_id':'$cast',total:{'$sum':1}}},{'$project':{_id:0,cast:'$_id',total:1}},{'$sort':{total:-1}}]";
        String query = "{'name':'张三'}";
        BasicQuery query1 = new BasicQuery(query);
        List<User> list = mongoTemplate.find(query1, User.class);
        System.out.printf(list.toString());
    }
}

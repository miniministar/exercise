package pattern.template.jdbc.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class JdbcFactory {

    private static DataSource source = null;

    static {
        Properties p = new Properties();
        try {
            p.load(JdbcFactory.class.getClassLoader().getResourceAsStream("druid.properties"));
            source = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return source;
    }


}

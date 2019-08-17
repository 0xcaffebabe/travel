package wang.ismy.travel.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;

/**
 * @author MY
 */
public class JdbcUtils {

    private static final DataSource DATA_SOURCE;

    static {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql:///travel");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        dataSource.setDriverClassLoader(Driver.class.getClassLoader());
        DATA_SOURCE = dataSource;

    }

    public static DataSource getDataSource(){
        return DATA_SOURCE;
    }
}

package io.github.kingqino.week07.assignment10.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class YamlDatasourceConfig {

    public DataSource generateDatasource()  {
        DataSource dataSource = null;
        try {
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File("/Users/yinghao1/Documents/Geek/Submission/Week7/week07/src/main/resources/orderConfig.yaml"));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return dataSource;
    }
}

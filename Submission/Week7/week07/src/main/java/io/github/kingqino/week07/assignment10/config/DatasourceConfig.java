package io.github.kingqino.week07.assignment10.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Component
public class DatasourceConfig {

    public DataSource generateDatasource() {
        // 配置数据源名称及数据源映射关系
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        HikariConfig masterConfig = new HikariConfig();
        masterConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        masterConfig.setJdbcUrl("jdbc:mysql://10.182.8.226:3316/db");
        masterConfig.setUsername("root");
        masterConfig.setPassword("root");
        DataSource masterDataSource = new HikariDataSource(masterConfig);
        dataSourceMap.put("ds_master", masterDataSource);

        HikariConfig slaveConfig1 = new HikariConfig();
        slaveConfig1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        slaveConfig1.setJdbcUrl("jdbc:mysql://10.182.8.226:3326/db");
        slaveConfig1.setUsername("root");
        slaveConfig1.setPassword("root");
        DataSource slaveDataSource1 = new HikariDataSource(slaveConfig1);
        dataSourceMap.put("ds_slave1", slaveDataSource1);

        HikariConfig slaveConfig2 = new HikariConfig();
        slaveConfig2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        slaveConfig2.setJdbcUrl("jdbc:mysql://10.182.8.227:3336/db");
        slaveConfig2.setUsername("root");
        slaveConfig2.setPassword("root");
        DataSource slaveDataSource2 = new HikariDataSource(slaveConfig2);
        dataSourceMap.put("ds_slave2", slaveDataSource2);

        // LOAD BALANCE ALGORITHM
        // 负载均衡使用随机分配策略 还有可选项 ROUND_ROBIN
        // https://shardingsphere.apache.org/document/5.0.0-alpha/en/user-manual/shardingsphere-jdbc/configuration/built-in-algorithm/load-balance/
        ReplicaQueryDataSourceRuleConfiguration orderDataSourceRuleConfiguration = new ReplicaQueryDataSourceRuleConfiguration("order", "ds_master", Arrays.asList("ds_slave1", "ds_slave2"), "RANDOM");

        // 将数据源配置添加到配置组内
        Collection<ReplicaQueryDataSourceRuleConfiguration> dataSources = new ArrayList<>();
        dataSources.add(orderDataSourceRuleConfiguration);
        ReplicaQueryRuleConfiguration configurations = new ReplicaQueryRuleConfiguration(dataSources, new HashMap<>());

        // 配置其他属性
        Properties properties = new Properties();
        properties.put("sql-show", "true");

        // 组装 =》 完成配置读写分离、负载均衡数据源
        DataSource dataSource = null;
        try {
            dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(configurations), properties);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dataSource;
    }

}

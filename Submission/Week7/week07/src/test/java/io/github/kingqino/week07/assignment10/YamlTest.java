package io.github.kingqino.week07.assignment10;

import io.github.kingqino.week07.assignment10.config.YamlDatasourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
public class YamlTest {

    @Autowired
    private YamlDatasourceConfig yamlDatasourceConfig;

    @Test
    void select() {
        // 配不合适，好像是官方的Bug
        DataSource dataSource = yamlDatasourceConfig.generateDatasource();

        String sql = "SELECT * FROM `order` WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, 1000007);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    long id = rs.getLong("id");
                    long userId = rs.getLong("user_id");
                    long productId = rs.getLong("product_id");

                    System.out.printf("id: %d,\t user_id: %d,\t product_id: %d\n", id, userId, productId);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

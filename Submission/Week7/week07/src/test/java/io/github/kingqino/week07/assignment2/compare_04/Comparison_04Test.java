package io.github.kingqino.week07.assignment2.compare_04;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
public class Comparison_04Test {
    public static final int RECORD_NUM = 100_000;

    @Autowired
    private DataSource ds;


    // 耗时：1277毫秒，即1秒
    @Test
    void hikariTest() {
        long start = System.currentTimeMillis();

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `order`(`user_id`,`product_id`) VALUES(?,?)");

            for (int i = 0; i < RECORD_NUM; i++){
                ps.setLong(1, (long) (Math.random() * 1_000_000_000));
                ps.setLong(2, (long) (Math.random() * 1_000_000_000));
                ps.addBatch();
            }

            ps.executeBatch();

            ps.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒，即" + (end - start) / 1000 + "秒");
    }
}

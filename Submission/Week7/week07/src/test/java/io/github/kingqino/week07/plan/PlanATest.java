package io.github.kingqino.week07.plan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class PlanATest {

    @Autowired
    DataSource ds;

    // 5个线程-单个线程插入数据量 耗时：9414毫秒，即9秒
    // 10个线程-单个线程插入数据量 耗时：11416毫秒，即11秒
    @Test
    void inertData() throws InterruptedException {
        long start = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(5);
        int recordNum = 200_000;

        for (int j = 0; j < 5; j++) {
            Thread thread = new Thread(() -> {
                try {
                    Connection connection = ds.getConnection();
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO `order`(`user_id`,`product_id`) VALUES(?,?)");

                    for (int i = 0; i < recordNum; i++){
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
                latch.countDown();
            });
            thread.start();
        }

        latch.await();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒，即" + (end - start) / 1000 + "秒");
    }
}

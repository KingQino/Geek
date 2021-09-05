package io.github.kingqino.week05.task_10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoJDBCTest {
    @Autowired
    DemoJDBC demoJDBC;

    @Autowired
    DemoHikariCP demoHikariCP;

    @Test
    void JDBCTest() {
        demoJDBC.JDBCTest();
    }

    @Test
    void HikariTest() {
        System.out.println(demoHikariCP.list());
    }
}

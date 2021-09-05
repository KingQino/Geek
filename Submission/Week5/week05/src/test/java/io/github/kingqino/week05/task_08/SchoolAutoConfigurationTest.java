package io.github.kingqino.week05.task_08;

import io.github.kingqino.week05.task_03.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchoolAutoConfigurationTest {
    @Autowired
    School school;

    @Test
    void test() {
        System.out.println(school);
    }
}

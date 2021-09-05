package io.github.kingqino.week05.task_02;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class AutowiringTest {

    @Autowired
    Person person;

    @Autowired
    Peach peach;

    @Test
    void firstAutowired() {
        person.introduce();
    }

    @Test
    void secondAutowired() {
        peach.info();
    }

    @Test
    void thirdAutowired() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml");
        Person mike = (Person) context.getBean("mike");
        mike.introduce();
    }
}

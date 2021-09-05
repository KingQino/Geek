package io.github.kingqino.week05.task_03;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class SchoolTest {

    @Test
    void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml");
        School school = (School) context.getBean("primary");
        System.out.println("这家学校的名字是：" + school.getName() + "，有" + school.getKlasses().size() + "个班级");
        System.out.println("    班级的名字是：" + school.getKlasses().get(0).getKlassName() + "，有" + school.getKlasses().get(0).getStudents().size() + "个学生");
        System.out.println("    学生信息为：");
        school.getKlasses().get(0).getStudents().forEach(System.out::println);

    }
}

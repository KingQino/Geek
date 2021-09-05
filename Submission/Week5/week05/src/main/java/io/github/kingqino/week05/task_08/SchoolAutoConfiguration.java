package io.github.kingqino.week05.task_08;

import io.github.kingqino.week05.task_03.Klass;
import io.github.kingqino.week05.task_03.School;
import io.github.kingqino.week05.task_03.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnClass(School.class)
@EnableConfigurationProperties(SchoolProperties.class)
@ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
@PropertySource("classpath:application.properties")
public class SchoolAutoConfiguration {

    @Autowired
    private SchoolProperties schoolProperties;

    @Bean
    public School school() {
        String schoolName = schoolProperties.getName();
        List<Integer> studentIds = schoolProperties.getStudentIds();
        List<String> studentNames = schoolProperties.getStudentNames();
        List<String> classNames = schoolProperties.getClassNames();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentIds.size(); i++) {
            students.add(new Student(studentIds.get(i), studentNames.get(i)));
        }

        List<Klass> klasses = new ArrayList<>();
        for (String className : classNames) {
            klasses.add(new Klass(className, students));
        }

        return new School(schoolName, klasses);
    }
}

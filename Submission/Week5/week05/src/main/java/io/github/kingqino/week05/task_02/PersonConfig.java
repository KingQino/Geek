package io.github.kingqino.week05.task_02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 第一种 在Java配置类中配置Bean
 */
@Configuration
@ComponentScan(value = "io.github.kingqino.week05.task_02")
public class PersonConfig {

    @Bean
    public Person person() {
        return new Person("张三", 90);
    }
}
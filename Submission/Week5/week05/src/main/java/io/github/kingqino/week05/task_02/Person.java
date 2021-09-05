package io.github.kingqino.week05.task_02;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class Person {
    private final String name;
    private final int age;

    public void introduce() {
        System.out.println("我的名字是" + this.name + ", 我的年龄是" + this.age);
    }
}

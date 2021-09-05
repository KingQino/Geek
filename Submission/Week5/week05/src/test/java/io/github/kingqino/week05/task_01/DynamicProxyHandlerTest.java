package io.github.kingqino.week05.task_01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class DynamicProxyHandlerTest {

    @Test
    void test() {
        PersonImpl person = new PersonImpl();
        Person proxy = (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new DynamicProxyHandler(person));
        proxy.doSomething();
    }
}

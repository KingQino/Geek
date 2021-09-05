package io.github.kingqino.week05.task_01;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
public class DynamicProxyHandler implements InvocationHandler {
    private final Object object;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(object, args);
        after();

        return result;
    }

    private void before() {
        System.out.println("开始日志记录...");
    }

    private void after() {
        System.out.println("完成日志记录...");
    }

}

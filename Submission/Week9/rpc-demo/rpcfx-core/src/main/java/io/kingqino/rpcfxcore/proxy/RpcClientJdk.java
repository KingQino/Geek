package io.kingqino.rpcfxcore.proxy;

import java.lang.reflect.Proxy;

public class RpcClientJdk extends RpcProxy implements RpcClient{

    @Override
    public <T> T create(Class<T> serviceClass, String url) {
        // 查询是否之前生成过，若是，则直接返回服务代理
        if (!isExit(serviceClass.getName())) {
            add(serviceClass.getName(), newProxy(serviceClass, url));
        }

        return (T) getProxy(serviceClass.getName());
    }

    private <T> T newProxy(Class<T> serviceClass, String url) {
        ClassLoader loader = RpcClientJdk.class.getClassLoader();
        Class[] classes = new Class[]{serviceClass};
        return (T) Proxy.newProxyInstance(loader, classes, new RpcInvocationHandler(serviceClass, url) );
    }
}

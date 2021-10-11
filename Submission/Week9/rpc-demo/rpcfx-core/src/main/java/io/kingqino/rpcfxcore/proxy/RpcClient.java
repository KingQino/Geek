package io.kingqino.rpcfxcore.proxy;

public interface RpcClient {

    /**
     * create proxy
     * @param serviceClass 服务类
     * @param url 服务器URL
     * @param <T> 参数
     * @return 代理类
     */
    <T> T create(final Class<T> serviceClass, final String url);
}

package io.kingqino.rpcfxcore.api;

import lombok.Data;

@Data
public class RpcRequest {

    /**
     * 接口名称 - 服务名称
     */
    private String serviceClass;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private Object[] argv;
}

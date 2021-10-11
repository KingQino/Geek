package io.kingqino.rpcfxcore.api;

import lombok.Data;

@Data
public class RpcResponse {
    /**
     * 响应结果
     */
    private Object result;

    /**
     * 请求是否执行成功
     */
    private boolean status;

    /**
     * 执行失败的异常信息
     */
    private Exception exception;

    public boolean getStatus() {
        return status;
    }
}

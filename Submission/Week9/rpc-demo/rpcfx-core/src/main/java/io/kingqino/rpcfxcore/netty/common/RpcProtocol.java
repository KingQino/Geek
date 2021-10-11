package io.kingqino.rpcfxcore.netty.common;

import lombok.Data;

/**
 * Netty 通信的数据格式
 */
@Data
public class RpcProtocol {
    /**
     * 数据长度
     */
    private int len;

    /**
     * 数据内容
     */
    private byte[] content;
}

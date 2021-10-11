package io.kingqino.rpcfxprovider.service.impl;

import io.kingqino.rpcfxapi.entity.Order;
import io.kingqino.rpcfxapi.service.OrderService;
import io.kingqino.rpcfxcore.exception.CustomException;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order findById(int id) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order findError() {
        throw new CustomException("Custom Exception");
    }
}

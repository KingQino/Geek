package io.kingqino.rpcfxapi.service;

import io.kingqino.rpcfxapi.entity.Order;

public interface OrderService {


    /**
     * find by id
     * @param id id
     * @return order
     */
    Order findById(int id);

    /**
     * return exception
     * @return exception
     */
    Order findError();
}

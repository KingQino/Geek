package io.kingqino.rpcfxapi.service;

import io.kingqino.rpcfxapi.entity.User;

public interface UserService {

    /**
     * find the record by id
     * @param id id
     * @return user
     */
    User findById(int id);
}

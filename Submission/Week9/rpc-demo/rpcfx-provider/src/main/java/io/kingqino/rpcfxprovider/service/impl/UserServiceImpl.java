package io.kingqino.rpcfxprovider.service.impl;

import io.kingqino.rpcfxapi.entity.User;
import io.kingqino.rpcfxapi.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "RPC----Service provided in the Server");
    }
}

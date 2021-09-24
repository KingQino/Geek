package io.github.kingqino.week07.assignment9.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kingqino.week07.assignment9.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void writeToMaster() {
        Order order = new Order();
        order.setUserId(83764586234L);
        order.setProductId(10000000000L);

        boolean b = orderService.insertOne(order);
        System.out.println("插入成功 - " + b);
    }

    @Test
    void readFromSlave() {
        long count = orderService.count();
        System.out.println(count);

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getId, 1_000_000);
        List<Order> list = orderService.list(queryWrapper);
        System.out.println(list);
    }
}

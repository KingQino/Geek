package io.github.kingqino.week07.assignment9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.kingqino.week07.assignment9.entity.Order;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinghao1
 * @since 2021-09-23
 */
public interface IOrderService extends IService<Order> {

    boolean insertOne(Order order);

    List<Order> selectOne(int id);
}

package io.github.kingqino.week07.assignment9.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.kingqino.week07.assignment9.entity.Order;
import io.github.kingqino.week07.assignment9.mapper.OrderMapper;
import io.github.kingqino.week07.assignment9.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinghao1
 * @since 2021-09-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Override
    @DS("master")
    @Transactional
    public boolean insertOne(Order order) {

        return baseMapper.insert(order) > 0;
    }

    @Override
    @DS("slave")
    public List<Order> selectOne(int id) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getId, id);

        return list(queryWrapper);
    }

}

package io.github.kingqino.week07.assignment9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kingqino.week07.assignment9.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yinghao1
 * @since 2021-09-23
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}

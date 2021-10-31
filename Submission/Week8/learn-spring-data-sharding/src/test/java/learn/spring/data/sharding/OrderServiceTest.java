package learn.spring.data.sharding;

import learn.spring.data.sharding.domain.entity.Order;
import learn.spring.data.sharding.service.IOrderService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Slf4j
public class OrderServiceTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void testInsert() {
        for (int i = 1; i <= 16; i++) {
            int insert = orderService.insert(buildOrder(i));
        }
        log.info("end");
    }

    @Test
    public void testSelectById() {
        for (int i = 1; i <= 16; i++) {
            Order order = orderService.selectById(i);
            assertNotNull(order);
            assertEquals(i, order.getId());
        }
        log.info("end");
    }

    @Test
    public void testListAllOrders() {
        List<Order> orders = orderService.listAllOrders();
        assertNotNull(orders);
        // TODO ... 查询时 0/1 会自动转换为 Boolean 类型
        assertEquals(16, orders.size());
    }

    @Test
    public void testCountAllOrders() {
        Integer integer = orderService.countAllOrders();
        assertEquals(16, integer);
    }

    @Test
    public void testDeleteById() {
        for (int i = 1; i <= 16; i++) {
            orderService.deleteById(i);
        }
        log.info("end");
    }

    private Order buildOrder(int id) {
        return Order.builder()
                .id(id)
                .orderSn("AAA")
                .customerId(id)
                .orderStatus((short)1)
                .createTime(new Date())
                .payTime(new Date())
                .shipTime(new Date())
                .receiveTime(new Date())
                .discountMoney(new BigDecimal("30"))
                .shipMoney(new BigDecimal("0"))
                .payMoney(new BigDecimal("99"))
                .payMethod((short) 1)
                .address("CHANGSHA")
                .receiveUser("Bob")
                .shipSn("BBB")
                .shipCompanyName("CCC")
                .build();
    }
}

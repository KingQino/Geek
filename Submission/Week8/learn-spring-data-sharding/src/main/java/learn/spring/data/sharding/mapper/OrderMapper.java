package learn.spring.data.sharding.mapper;

import learn.spring.data.sharding.domain.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class OrderMapper {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void init() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 新增订单
     *
     * @param order 订单实体类
     * @return int
     */
    public int insert(Order order) {
        String insertSql = "INSERT INTO mydb.t_order_master" +
                "(id, order_sn, customer_id, order_status, create_time, ship_time, pay_time, receive_time, discount_money, ship_money, pay_money, pay_method, address, receive_user, ship_sn, ship_company_name) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        String insertSql = "INSERT INTO mydb.t_order_master\n" +
//                "(id,\n" +
//                "order_sn,\n" +
//                "customer_id,\n" +
//                "order_status,\n" +
//                "create_time,\n" +
//                "ship_time,\n" +
//                "pay_time,\n" +
//                "receive_time,\n" +
//                "discount_money,\n" +
//                "ship_money,\n" +
//                "pay_money,\n" +
//                "pay_method,\n" +
//                "address,\n" +
//                "receive_user,\n" +
//                "ship_sn,\n" +
//                "ship_company_name)\n" +
//                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insertSql, ps -> setOrderValues(ps, order));
    }

    /**
     * 根据订单 ID 查询订单
     *
     * @param id 订单 ID
     * @return 订单
     */
    public Order selectById(int id) {
        String selectSql = "select * from mydb.t_order_master where id = ?";
        return jdbcTemplate.queryForObject(selectSql, this::getOrderValues, id);
    }

    /**
     * 查询全部订单数据
     *
     * @return 订单列表
     */
    public List<Order> listAllOrders() {
        String selectSql = "select * from mydb.t_order_master";
        return jdbcTemplate.query(selectSql,  this::getOrderValues);
    }

    /**
     * 查询全部订单数量
     *
     * @return 订单列表
     */
    public Integer countAllOrders() {
        String sql = "select count(*) from mydb.t_order_master";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 根据订单 ID 删除 订单
     *
     * @param id 订单 ID
     * @return int
     */
    public int deleteById(int id) {
        String deleteSql = "delete from mydb.t_order_master where id = ?";
        return jdbcTemplate.update(deleteSql, id);
    }

    /**
     * 保存时，设置订单值
     *
     * @param ps    PreparedStatement
     * @param order 订单实体类
     * @throws SQLException SQLException
     */
    private void setOrderValues(PreparedStatement ps, Order order) throws SQLException {
        ps.setInt(1, order.getId());
        ps.setString(2, order.getOrderSn());
        ps.setInt(3, order.getCustomerId());
        ps.setShort(4, order.getOrderStatus());
        ps.setDate(5, new java.sql.Date(order.getCreateTime().getTime()));
        ps.setDate(6, new java.sql.Date(order.getShipTime().getTime()));
        ps.setDate(7, new java.sql.Date(order.getPayTime().getTime()));
        ps.setDate(8, new java.sql.Date(order.getReceiveTime().getTime()));
        ps.setBigDecimal(9, order.getDiscountMoney());
        ps.setBigDecimal(10, order.getShipMoney());
        ps.setBigDecimal(11, order.getPayMoney());
        ps.setShort(12, order.getPayMethod());
        ps.setString(13, order.getAddress());
        ps.setString(14, order.getReceiveUser());
        ps.setString(15, order.getShipSn());
        ps.setString(16, order.getShipCompanyName());
    }

    /**
     * 读取时，获取订单值
     */
    private Order getOrderValues(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder()
                .id(rs.getInt(1))
                .orderSn(rs.getString(2))
                .customerId(rs.getInt(3))
                .orderStatus(rs.getShort(4))
                .createTime(rs.getDate(5))
                .shipTime(rs.getDate(6))
                .payTime(rs.getDate(7))
                .receiveTime(rs.getDate(8))
                .discountMoney(rs.getBigDecimal(9))
                .shipMoney(rs.getBigDecimal(10))
                .payMoney(rs.getBigDecimal(11))
                .payMethod(rs.getShort(12))
                .address(rs.getString(13))
                .receiveUser(rs.getString(14))
                .shipSn(rs.getString(15))
                .shipCompanyName(rs.getString(16))
                .build();
    }

}

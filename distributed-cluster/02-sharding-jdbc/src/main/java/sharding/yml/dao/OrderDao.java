package sharding.yml.dao;

import sharding.yml.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    long addOne(Order order);

    Order selectOne(@Param("order_id") long orderId, @Param("user_id") int userId);

    List<Order> getOrderByUserId(long id);

}

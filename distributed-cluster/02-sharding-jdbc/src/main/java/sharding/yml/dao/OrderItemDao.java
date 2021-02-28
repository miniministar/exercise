package sharding.yml.dao;

import sharding.yml.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    void addOne(OrderItem orderItem);

    List<OrderItem> getByOrderId(int id);

    List<OrderItem> getOrderItemByPrice(int price);


}

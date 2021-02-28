package sharding.yml.entity;


import lombok.Data;

@Data
public class OrderItem {

    private long userId;
    private long orderItemId;
    private long orderId;
    private String name;
    private long price;

}

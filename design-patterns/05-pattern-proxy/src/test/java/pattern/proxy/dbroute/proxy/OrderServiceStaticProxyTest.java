package pattern.proxy.dbroute.proxy;

import org.junit.Test;
import pattern.proxy.dbroute.IOrderService;
import pattern.proxy.dbroute.Order;
import pattern.proxy.dbroute.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceStaticProxyTest {

    @Test
    public void createOrder() {
        try {
            Order order = new Order();
            order.setCreateTime(new Date().getTime());
            IOrderService orderService = new OrderServiceStaticProxy(new OrderService());
            orderService.createOrder(order);

            SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
            order.setCreateTime(df.parse("2017-01-01").getTime());
            orderService.createOrder(order);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
package pattern.strategy.pay;

import org.junit.Test;
import pattern.strategy.pay.payport.PayStrategy;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void pay() {
        Order order1 = new Order("1", "1", 10);
        MsgResult pay = order1.pay(PayStrategy.ALI_PAY);

        Order order2 = new Order("1", "1", 200);
        MsgResult pay1 = order2.pay(PayStrategy.WECHAT_PAY);
        System.out.println(pay);
        System.out.println(pay1);
    }
}
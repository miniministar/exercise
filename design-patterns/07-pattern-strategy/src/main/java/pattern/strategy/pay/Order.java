package pattern.strategy.pay;

import pattern.strategy.pay.payport.PayMent;
import pattern.strategy.pay.payport.PayStrategy;

public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid,String orderId,double amount){
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public MsgResult pay() {
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public MsgResult pay(String payKey) {
        PayMent payMent = PayStrategy.get(payKey);
        System.out.println("欢迎使用：" + payMent.getName() );
        System.out.println("本次交易金额：" + amount + ", 开始扣款...");
        return payMent.pay(uid, amount);
    }
}

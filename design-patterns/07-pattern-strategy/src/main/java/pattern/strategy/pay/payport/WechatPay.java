package pattern.strategy.pay.payport;

public class WechatPay extends PayMent {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 10;
    }
}

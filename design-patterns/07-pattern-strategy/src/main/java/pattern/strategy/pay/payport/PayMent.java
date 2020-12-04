package pattern.strategy.pay.payport;

import pattern.strategy.pay.MsgResult;

public abstract class PayMent {
    public abstract String getName();

    protected abstract double queryBalance(String uid);

    public MsgResult pay(String uid, double amount) {
        if(queryBalance(uid) < amount) {
            return new MsgResult(500, "支付失败", "余额不足");
        }
        return new MsgResult(200, "支付成功", "支付金额" + amount);
    }
}

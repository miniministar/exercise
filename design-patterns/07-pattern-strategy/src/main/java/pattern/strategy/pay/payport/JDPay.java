package pattern.strategy.pay.payport;

public class JDPay extends PayMent {
    @Override
    public String getName() {
        return "京东白条";
    }

    @Override
    protected double queryBalance(String uid) {
        return 400;
    }
}

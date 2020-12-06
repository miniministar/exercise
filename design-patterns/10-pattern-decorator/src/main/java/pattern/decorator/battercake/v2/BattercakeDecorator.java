package pattern.decorator.battercake.v2;

public abstract class BattercakeDecorator extends Battercake{
    //静态代理
    private Battercake battercake;
    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }
    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }
}

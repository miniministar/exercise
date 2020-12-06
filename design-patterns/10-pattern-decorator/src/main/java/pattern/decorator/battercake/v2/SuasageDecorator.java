package pattern.decorator.battercake.v2;

public class SuasageDecorator extends BattercakeDecorator {
    public SuasageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}

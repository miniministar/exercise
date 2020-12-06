package pattern.decorator.battercake.v2;

import org.junit.Test;

import static org.junit.Assert.*;

public class BattercakeTest {
    @Test
    public void decorator() {
        Battercake battercake;

        battercake = new BaseBattercake();
        battercake = new EggDecorator(battercake);
        battercake = new EggDecorator(battercake);
        battercake = new SuasageDecorator(battercake);
        System.out.println(battercake.getMsg() + ",总价：" + battercake.getPrice());
    }
}
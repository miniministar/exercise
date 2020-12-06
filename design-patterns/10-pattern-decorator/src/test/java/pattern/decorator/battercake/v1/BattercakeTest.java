package pattern.decorator.battercake.v1;

import org.junit.Test;
import pattern.decorator.battercake.v2.BaseBattercake;
import pattern.decorator.battercake.v2.EggDecorator;
import pattern.decorator.battercake.v2.SuasageDecorator;

import static org.junit.Assert.*;

public class BattercakeTest {

    @Test
    public void getMsg() {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getMsg() + "，总价格：" + battercake.getPrice());
        BattercakeWithEgg battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getMsg() + "，总价格：" + battercakeWithEgg.getPrice());
        BattercakeWithEggAndSausage battercakeWithEggAndSausage = new BattercakeWithEggAndSausage();
        System.out.println(battercakeWithEggAndSausage.getMsg() + "，总价格：" + battercakeWithEggAndSausage.getPrice());

    }

}
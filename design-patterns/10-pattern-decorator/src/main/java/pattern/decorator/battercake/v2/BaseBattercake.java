package pattern.decorator.battercake.v2;

public class BaseBattercake extends Battercake {
    protected String getMsg(){
        return "煎饼";
    }

    protected int getPrice(){
        return 5;
    }
}

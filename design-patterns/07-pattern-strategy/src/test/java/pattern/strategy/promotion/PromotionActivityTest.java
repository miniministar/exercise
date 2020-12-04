package pattern.strategy.promotion;

import org.junit.Test;

import static org.junit.Assert.*;

public class PromotionActivityTest {

    @Test
    public void execute() {
        PromotionActivity activity618 = new PromotionActivity(new ConponStrategy());
        activity618.execute();

        new PromotionActivity(new CashbackStrategy()).execute();
    }

    @Test
    public void factory() {
        String promotionKey = "GROUPBUY";
        PromotionActivity activity = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey));
        activity.execute();
    }
}
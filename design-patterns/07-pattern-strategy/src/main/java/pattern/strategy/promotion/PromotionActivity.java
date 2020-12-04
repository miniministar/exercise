package pattern.strategy.promotion;

public class PromotionActivity  {
    PromotionStrategy promotionStrategy;
    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }
    public void execute(){
        this.promotionStrategy.doPromotion();
    }
}

package pattern.delegate.simple;

public class BossTest {

    @org.junit.Test
    public void doing() {
        Boss boss = new Boss();
        boss.doing("编码");
        boss.doing("架构");
    }
}
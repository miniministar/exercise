package pattern.proxy.dynamic.cglib;

import org.junit.Test;
import pattern.proxy.dynamic.jdk.Girl;

public class CglibProxyTest {

    @Test
    public void intercept() {
        try {
            Girl girl = (Girl) new CglibProxy().getInstance(new Girl().getClass());
            girl.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
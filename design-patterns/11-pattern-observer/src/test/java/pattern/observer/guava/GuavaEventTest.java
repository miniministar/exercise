package pattern.observer.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuavaEventTest {

    @Test
    public void subscribe() {
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("guava event");
        //从Struts到SpringMVC的升级
        //因为Struts面向的类，而SpringMVC面向的是方法

        //前面两者面向的是类，Guava面向是方法

        //能够轻松落地观察模式的一种解决方案
        //MQ
    }
}
package pattern.observer.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;
import pattern.observer.advice.Question;

import static org.junit.Assert.*;

public class GuavaAdviceTest {

    @Test
    public void publishQuestion() {
        EventBus bus = new EventBus();
        GuavaAdvice advice = new GuavaAdvice();
        bus.register(advice);

        GuavaTeacher teacher = new GuavaTeacher("guava 大师");
        bus.register(teacher);
        bus.post(new Question().setUserName("张三").setContent("guava 发布问题"));

        GuavaTeacher teacher2 = new GuavaTeacher("guava 大师2");
        bus.register(teacher2);

        bus.post(new Question().setUserName("李四").setContent("guava 发布问题"));


    }
}
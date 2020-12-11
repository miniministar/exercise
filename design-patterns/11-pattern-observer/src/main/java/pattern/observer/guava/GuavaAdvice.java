package pattern.observer.guava;

import com.google.common.eventbus.Subscribe;
import pattern.observer.advice.Question;

public class GuavaAdvice {
    private String name = "guava通知生态圈";
    @Subscribe
    public void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题。" );
    }
}

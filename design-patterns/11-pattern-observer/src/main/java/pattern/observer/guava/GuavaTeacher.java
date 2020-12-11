package pattern.observer.guava;

import com.google.common.eventbus.Subscribe;
import pattern.observer.advice.Question;

public class GuavaTeacher {
    private String name ;

    public GuavaTeacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Subscribe
    public void receiveAdvice(Question question) {
        System.out.println("===============================================");
        System.out.println(this.name + "老师，你好！\n"
                + "您收到一个来自" +  question.getUserName() + "的提问，问题如下：\n" + question.getContent() +"\n"
                + "提问者：" + question.getUserName());
    }

}

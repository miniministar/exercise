package pattern.observer.advice;

import java.util.Observable;
import java.util.Observer;

public class Teacher implements Observer {

    private String name;
    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Advice advice = (Advice)o;
        Question question = (Question) arg;

        System.out.println("===============================================");
        System.out.println(name + "老师，你好！\n"
        + "您收到一个来自" + advice.getName() + "的提问，问题如下：\n" + question.getContent() +"\n"
        + "提问者：" + question.getUserName());
    }
}

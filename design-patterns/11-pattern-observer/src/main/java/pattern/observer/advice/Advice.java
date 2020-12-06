package pattern.observer.advice;

import java.util.Observable;

public class Advice extends Observable {
    private String name = "通知生态圈";
    private static Advice advice = new Advice();
    private Advice() {}

    public static Advice getInstance(){
        return advice;
    }

    public String getName() {
        return name;
    }

    public void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题。" );
        setChanged();
        notifyObservers(question);
    }
}

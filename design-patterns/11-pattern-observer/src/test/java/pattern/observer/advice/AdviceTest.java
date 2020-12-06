package pattern.observer.advice;

public class AdviceTest {

    @org.junit.Test
    public void publishQuestion() {
        Advice advice = Advice.getInstance();
        Teacher teacher = new Teacher("teacher");
        Teacher xiaohong = new Teacher("小红");

        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者模式适用于那些场景？");

        advice.addObserver(teacher);
        Question question2 = new Question();
        question2.setUserName("小张");
        question2.setContent("模板模式必须用抽象类吗？");
        advice.publishQuestion(question2);

        advice.addObserver(xiaohong);
        advice.publishQuestion(question);



    }
}
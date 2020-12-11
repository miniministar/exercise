package pattern.observer.advice;

public class Question {

    private String userName;
    private String content;
    public String getUserName() {
        return userName;
    }

    public Question setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Question setContent(String content) {
        this.content = content;
        return this;
    }
}

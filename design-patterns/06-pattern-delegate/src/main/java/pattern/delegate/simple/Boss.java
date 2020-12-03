package pattern.delegate.simple;

public class Boss {
    private Leader leader;
    public Boss() {
        leader = new Leader();
    }

    public void doing(String command) {
        leader.doing(command);
    }
}

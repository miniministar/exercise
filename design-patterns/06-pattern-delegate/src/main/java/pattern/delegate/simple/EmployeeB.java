package pattern.delegate.simple;

public class EmployeeB implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工B，我擅长架构，正在" + command);
    }
}

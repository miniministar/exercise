package pattern.delegate.simple;

public class EmployeeA implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工A，我擅长编码，正在" + command);
    }
}

package pattern.singleton;

public class ContainerSingletonTest1 {
    public static void main(String[] args) {
        Object bean = ContainerSingleton.getBean("pattern.bean.User");
        Object bean2 = ContainerSingleton.getBean("pattern.bean.User");
        System.out.println(bean);
        System.out.println(bean2);
        System.out.println(bean == bean2);

    }
}

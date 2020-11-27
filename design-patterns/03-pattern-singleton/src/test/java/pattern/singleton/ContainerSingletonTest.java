package pattern.singleton;

import pattern.executor.ConcurrentExecutor;

public class ContainerSingletonTest {
    public static void main(String[] args) {
        try {
            ConcurrentExecutor.execute(new ConcurrentExecutor.RunHandler() {
                @Override
                public void handler() {
                    Object bean = ContainerSingleton.getBean("pattern.bean.User");
                    System.out.println(bean);
                }
            }, 10, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

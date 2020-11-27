package pattern.singleton;

public class InnerLazySingleton {
    private InnerLazySingleton() {
        if(LazyHolder.LAZY != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    public static final InnerLazySingleton getInstance() {
        return LazyHolder.LAZY;
    }

    public static class LazyHolder{
        private static final InnerLazySingleton LAZY = new InnerLazySingleton();
    }
}

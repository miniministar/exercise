package pattern.singleton;

import java.lang.reflect.Constructor;

public class InnerLazySingletonTest {
    public static void main(String[] args) {
        try {
            Class<InnerLazySingleton> clazz = InnerLazySingleton.class;
            Constructor<InnerLazySingleton> c = clazz.getDeclaredConstructor(null);
            c.setAccessible(true);
            InnerLazySingleton singleton1 = c.newInstance();
            InnerLazySingleton singleton2 = c.newInstance();
            System.out.println(singleton1 == singleton2);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

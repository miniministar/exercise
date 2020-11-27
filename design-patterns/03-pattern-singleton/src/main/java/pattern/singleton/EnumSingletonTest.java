package pattern.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumSingletonTest {
    public static void main(String[] args) {

        testClass();


        EnumSingleton singleton = EnumSingleton.getInstance();
        singleton.setData(new Object());
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SerialeSingleton.obj"));
            out.writeObject(singleton);
            out.flush();
            out.close();

            ObjectInputStream input = new ObjectInputStream(new FileInputStream("SerialeSingleton.obj"));
            EnumSingleton singleton1 = (EnumSingleton) input.readObject();
            input.close();
            System.out.println(singleton.getData());
            System.out.println(singleton1.getData());
            System.out.println(singleton.getData() == singleton1.getData());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testClass() {
        try {
            Class<EnumSingleton> clazz = EnumSingleton.class;
            Constructor<EnumSingleton> c = null;
            c = clazz.getDeclaredConstructor(
                    String.class, int.class
            );
            c.setAccessible(true);
            EnumSingleton singleton = c.newInstance();
            System.out.println(singleton);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

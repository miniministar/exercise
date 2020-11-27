package pattern.singleton;

import java.io.*;

public class SeriableSingletonTest {
    public static void main(String[] args) {

        SeriableSingleton singleton = SeriableSingleton.getInstance();

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SerialeSingleton.obj"));
            out.writeObject(singleton);
            out.flush();
            out.close();

            ObjectInputStream input = new ObjectInputStream(new FileInputStream("SerialeSingleton.obj"));
            SeriableSingleton singleton1 = (SeriableSingleton) input.readObject();
            input.close();
            System.out.println(singleton);
            System.out.println(singleton1);
            System.out.println(singleton == singleton1);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

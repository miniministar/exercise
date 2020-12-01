package pattern.proxy.dynamic.myproxy;

import org.junit.Test;
import pattern.proxy.Person;
import pattern.proxy.dynamic.jdk.Girl;

import static org.junit.Assert.*;

public class MyProxyTest {

    @Test
    public void invoke() {
        try {
            Person person = (Person) new MyProxy().getInstance(new Girl());
            System.out.println(person.getClass());
            person.findLove();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
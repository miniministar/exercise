package pattern.proxy.dynamic.jdk;


import org.junit.Test;
import pattern.proxy.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JDKProxyTest {

    public static void main(String[] args) {
        try {
            Person person = (Person) new JDKProxy().getInstance(new Girl());
            Method findLove =  person.getClass().getMethod("findLove", null);
            findLove.invoke(person);

            person.findLove();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invoke() {
        Person person = (Person) new JDKProxy().getInstance(new Girl());
        person.findLove();
    }
}
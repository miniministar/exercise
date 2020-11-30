package pattern.prototype.simple;

import java.util.Arrays;
import java.util.List;

public class PrototypeATest {

    public static void main(String[] args) {
        PrototypeA prototypeA = new PrototypeA();
        prototypeA.setAge(12);
        prototypeA.setName("zhangsan");
        List<String> hobbies = Arrays.asList("swimming", "play basketball");
        prototypeA.setHobbies(hobbies);

        PrototypeA prototypeA1 = prototypeA.clone();
        System.out.println("克隆对象中引用地址：" + prototypeA1.getHobbies());
        System.out.println("原始对象中引用地址：" + prototypeA.getHobbies());

        System.out.println("浅克隆中引用对象地址相等：" + (prototypeA.getHobbies() == prototypeA1.getHobbies()) );

    }
}
package pattern.prototype.simple;

import java.util.List;

public class PrototypeA implements Prototype {
    private Integer age;
    private String name;
    private List hobbies;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobbies() {
        return hobbies;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public PrototypeA clone() {
        PrototypeA prototypeA = new PrototypeA();
        prototypeA.setAge(this.age);
        prototypeA.setName(this.name);
        prototypeA.setHobbies(this.hobbies);
        return prototypeA;
    }
}

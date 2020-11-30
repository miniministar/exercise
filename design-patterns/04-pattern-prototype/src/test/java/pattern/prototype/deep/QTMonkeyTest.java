package pattern.prototype.deep;

import org.junit.Test;

import static org.junit.Assert.*;

public class QTMonkeyTest {

    @Test
    public void clone1() {

        QTMonkey qtMonkey = new QTMonkey();
        try {
            QTMonkey copy = qtMonkey.clone();
            System.out.println("深克隆：" + (qtMonkey.getJingGuBang() == copy.getJingGuBang()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        QTMonkey shallowCopy = qtMonkey.shallowClone(qtMonkey);
        System.out.println("浅克隆：" + (qtMonkey.getJingGuBang() == shallowCopy.getJingGuBang()));
    }
}
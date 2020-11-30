package pattern.prototype.deep;

import java.io.*;
import java.util.Date;

public class QTMonkey extends Monkey implements Cloneable, Serializable {

    private JingGuBang jingGuBang;

    public JingGuBang getJingGuBang() {
        return jingGuBang;
    }

    public void setJingGuBang(JingGuBang jingGuBang) {
        this.jingGuBang = jingGuBang;
    }

    public QTMonkey() {
        this.setBirthday(new Date());
        this.jingGuBang = new JingGuBang();
    }

    @Override
    protected QTMonkey clone() throws CloneNotSupportedException {
        return  this.deepClone();
    }

    private QTMonkey deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(this);

            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            QTMonkey copyQtMonkey = (QTMonkey) is.readObject();
            copyQtMonkey.setBirthday(new Date());
            return copyQtMonkey;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public QTMonkey shallowClone(QTMonkey target) {
        QTMonkey copy = new QTMonkey();
        copy.setHeight(target.getHeight());
        copy.setWeight(target.getWeight());
        copy.setBirthday(new Date());
        copy.setJingGuBang(target.getJingGuBang());
        return copy;
    }
}

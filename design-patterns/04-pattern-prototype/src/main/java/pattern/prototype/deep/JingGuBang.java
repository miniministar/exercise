package pattern.prototype.deep;

import java.io.Serializable;

public class JingGuBang implements Serializable {
    private float h = 100;
    private float d = 10;

    public float getH() {
        return h;
    }

    public float getD() {
        return d;
    }

    public void big() {
        this.h *= 2;
        this.d *= 2;
    }

    public void small() {
        this.h /= 2;
        this.d /= 2;
    }

}

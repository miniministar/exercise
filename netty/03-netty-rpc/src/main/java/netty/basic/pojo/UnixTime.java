package netty.basic.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnixTime {
    private final long value;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }
    public long value() {
        return this.value;
    }

    @Override
    public String toString() {
        return df.format(new Date((value() - 2208988800L) * 1000L));
    }

}

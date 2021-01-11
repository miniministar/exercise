package netty.rpc.protocol;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class InvokerProtocol implements Serializable {
    private String className;
    private String methodName;
    private Class[] params;
    private Object[] values;
}

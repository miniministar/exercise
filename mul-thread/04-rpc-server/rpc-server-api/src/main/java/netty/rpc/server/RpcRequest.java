package netty.rpc.server;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] parameters;
    private String version;
}

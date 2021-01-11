package netty.rpc.server;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String name;
    private int age;
}

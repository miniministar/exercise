package rabbitmq.project.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Merchant implements Serializable {
    int id; // 商户编号
    String name; // 商户名称
    String address; // 商户地址
    String accountNo; // 商户账号
    String accountName; // 户名
    String state; // 状态 1 激活 2 关闭
    String stateStr; // 状态中文

    public Merchant() {
    }

    public Merchant(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getStateStr() {
        if(null == state){
            return "";
        }else if("1".equals(state.toString())){
            return "激活";
        }else if ("0".equals(state.toString())){
            return "关闭";
        }else{
            return "未知";
        }
    }
}

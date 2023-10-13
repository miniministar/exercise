package org.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.example.springboot.validate.Create;
import org.example.springboot.validate.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
@Getter
@Setter
@TableName("t_user")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {Create.class, Update.class})
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空", groups = {Create.class, Update.class})
    private String nickname;

    /**
     * 性别：1-男 2-女
     */
    @NotNull(message = "性别不能为空", groups = {Create.class, Update.class})
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 联系方式
     */
    @NotBlank(message = "电话不能为空", groups = {Create.class, Update.class})
    private String mobile;

    /**
     * 用户状态：1-正常 0-禁用
     */
    private Boolean status;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 逻辑删除标识：0-未删除；1-已删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}

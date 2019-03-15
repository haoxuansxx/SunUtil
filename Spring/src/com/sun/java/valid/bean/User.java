package valid.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户类
 *
 * @Author Sun
 * @date 2019-03-12
 */
public class User {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为NULL！")
    private String id;

    /**
     * 登录用户名
     */
    @NotBlank(message = "登录用户名不能为空！")
    private String username;

    /**
     * 登录密码
     */
    @NotEmpty(message = "登录密码不能为空！")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{id: " + this.id + ", " +
                "username: " + this.username + ", " +
                "password: " + this.password + "}";
    }

}

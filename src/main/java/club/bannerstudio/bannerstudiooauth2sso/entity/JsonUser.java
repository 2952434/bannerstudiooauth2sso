package club.bannerstudio.bannerstudiooauth2sso.entity;

import java.util.Map;

/**
 * @Author: Ben
 * @Date: 2022/1/10 0:28
 */
public class JsonUser {

    private String authority;

    public JsonUser(String authority) {
        this.authority = authority;
    }

    public JsonUser() {
    }


    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "JsonUser{" +
                "authority='" + authority + '\'' +
                '}';
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

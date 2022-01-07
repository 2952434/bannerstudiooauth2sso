package club.bannerstudio.bannerstudiooauth2sso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Ben
 * @Date: 2022/1/6 1:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthUser implements UserDetails {


    /**
     * id
     */
    private Integer id;
    /**
     *用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * BannerId
     */
    private Integer bannerId;

    private Collection<? extends GrantedAuthority> authorities;


    public AuthUser(User user,Integer bannerId) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
        this.bannerId=bannerId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password ;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

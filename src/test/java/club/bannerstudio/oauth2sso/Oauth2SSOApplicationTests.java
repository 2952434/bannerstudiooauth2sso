package club.bannerstudio.oauth2sso;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Oauth2SSOApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void test(){
        System.out.println(passwordEncoder.encode("bannerstudioofficialwebsite"));
    }
}

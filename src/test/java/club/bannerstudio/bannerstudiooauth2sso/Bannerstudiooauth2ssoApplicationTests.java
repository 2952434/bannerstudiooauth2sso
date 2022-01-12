package club.bannerstudio.bannerstudiooauth2sso;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class Bannerstudiooauth2ssoApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }


    @Test
    public void test(){
        String str = "78458";
        String pattern = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }

}

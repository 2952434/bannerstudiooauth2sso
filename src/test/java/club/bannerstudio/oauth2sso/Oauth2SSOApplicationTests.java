package club.bannerstudio.oauth2sso;

import club.bannerstudio.oauth2sso.service.IMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class Oauth2SSOApplicationTests {

    @Autowired
    IMemberService iMemberService;
    @Test
    void contextLoads() {
        System.out.println(
                iMemberService.selectHeadUrlByUserName("wanghaoyuan"));
    }

}

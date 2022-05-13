package club.bannerstudio.oauth2sso;

import club.bannerstudio.oauth2sso.mapper.MemberMapper;
import club.bannerstudio.oauth2sso.service.IMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;


@SpringBootTest
class Oauth2SSOApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    IMemberService iMemberService;

    @Autowired
    private MemberMapper memberMapper;
    @Test
    void contextLoads() {
//        List<Map<String, String>> maps = memberMapper.selectDirectionGroupBy();
//        System.out.println(maps);

//        List<Map<String, String>> maps1 = memberMapper.selectGradeGroupBy();
//        System.out.println(maps1);

//        List<Map<String, String>> web = memberMapper.selectUserIdAndMemberName("Web", "2021级");
//        System.out.println(web);
    }

}

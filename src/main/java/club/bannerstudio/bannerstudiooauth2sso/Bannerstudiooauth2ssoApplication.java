package club.bannerstudio.bannerstudiooauth2sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Bannerstudiooauth2ssoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bannerstudiooauth2ssoApplication.class, args);
    }

}

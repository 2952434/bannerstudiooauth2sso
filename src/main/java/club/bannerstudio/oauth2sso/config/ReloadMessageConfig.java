package club.bannerstudio.oauth2sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @Author: Ben
 * @Date: 2022/1/8 16:01
 */
@Configuration
public class ReloadMessageConfig {
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 指定类路径的时候，不需要添加文件后缀[.properties]
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        return messageSource;
    }
}

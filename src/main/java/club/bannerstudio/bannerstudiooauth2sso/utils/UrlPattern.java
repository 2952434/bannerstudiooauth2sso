package club.bannerstudio.bannerstudiooauth2sso.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Ben
 * @Date: 2022/1/9 19:44
 */
public class UrlPattern {

    public String getParamByUrl(String url, String name) {
        url += "&";
        String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if (m.find( )) {
            return m.group(0).split("=")[1].replace("&", "");
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String string="DefaultSavedRequest [http://localhost:8081/oauth/authorize?client_id=javaboy&response_type=code&scope=all&redirect_uri=http://localhost:8084/callback]";
//        System.out.println(UrlPattern.getParamByUrl(string,"client_id"));
//        System.out.println(UrlPattern.getParamByUrl(string,"scope"));
    }
}

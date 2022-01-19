//package club.bannerstudio.bannerstudiooauth2sso.handler;
//
//import club.bannerstudio.bannerstudiooauth2sso.utils.RespBean;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
///**
// * @Author: Ben
// * @Date: 2022/1/8 0:07
// */
//public class AuthExceptionHandler {
//
//
//
//    /**
//     * 用户名和密码错误
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(InvalidGrantException.class)
//    public RespBean handleInvalidGrantException(InvalidGrantException e) {
//        return RespBean.error("用户名和密码输入错误");
//    }
//
//    /**
//     * 账户异常(禁用、锁定、过期)
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler({InternalAuthenticationServiceException.class})
//    public RespBean handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
//        return RespBean.error(e.getMessage());
//    }
//}

package com.ruoyi.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.framework.security.LoginUser;

/**
 * @author 终于白发始于青丝
 * @Classname SecurityUtils
 * @Description 类方法说明：安全服务工具类
 * @Date 2022/3/25 下午 14:02
 */
public class SecurityUtils {

    /**
     * 用户ID
     **/
    public static Long getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new ServiceException("获取用户ID异常" , HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取部门ID
     **/
    public static Long getDeptId() {
        try {
            return getLoginUser().getDeptId();
        } catch (Exception e) {
            throw new ServiceException("获取部门ID异常" , HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常" , HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException("获取用户信息异常" , HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname encryptPassword
     * @Description 类方法说明：生成BCryptPasswordEncoder密码
     * @Return 返回值：java.lang.String 加密字符串
     * @Params java.lang.String password 密码
     * @Date 2022/3/25 下午 14:02
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname matchesPassword
     * @Description 类方法说明：判断密码是否相同
     * @Return 返回值：boolean 结果
     * @Params java.lang.String rawPassword 加密后字符
     * @Params java.lang.String encodedPassword 真实密码
     * @Date 2022/3/25 下午 14:02
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname isAdmin
     * @Description 类方法说明：是否为管理员
     * @Return 返回值：boolean 结果
     * @Params java.lang.Long userId 用户ID
     * @Date 2022/3/25 下午 14:03
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}

package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.UserAddress;
import cn.wolfcode.shop.domain.UserInfo;
import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.WSException;

/**
 * 用户相关接口
 */
public interface IUserService {
    /**
     * 注册接口
     * @param login
     * @param phone
     */
    void register (UserLogin login, String phone) throws WSException;

    /**
     * 登录
     * @param login
     * @return
     */
    String login(UserLogin login);

    /**
     * 根据认证令牌获取redis缓存中的对象
     * @param token
     * @return
     */
    UserInfo getUserByToken(String token);

    UserAddress getAddressById(Long userAddressId);

    /**
     * 注销用户的方法
     * @param token
     */
    void logout(String token);
}

package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.UserAddress;
import cn.wolfcode.shop.domain.UserInfo;
import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.WSException;
import cn.wolfcode.shop.mapper.UserAddressMapper;
import cn.wolfcode.shop.mapper.UserInfoMapper;
import cn.wolfcode.shop.mapper.UserLoginMapper;
import cn.wolfcode.shop.redis.RedisServiceImpl;
import cn.wolfcode.shop.redisUtil.UserKeyPrefix;
import cn.wolfcode.shop.service.IUserService;
import cn.wolfcode.shop.util.AssertUtils;
import cn.wolfcode.shop.utils.PasswordUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLoginMapper loginMapper;

    @Autowired
    private RedisServiceImpl redisServiceImpl ;
    @Autowired
    private UserAddressMapper addressMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserLogin login, String phone) throws WSException {
        //检验当前传入的数据是否正确
        AssertUtils.isNull(login.getUsername(),"用户名不能为空");
        AssertUtils.isNull(login.getPassword(),"密码不能为空");
        AssertUtils.isNull(phone,"手机号码不能为空");
        //校验当前用户名是否已经被注册
        AssertUtils.handlerCheck(!(loginMapper.getByUsername(login.getUsername())==null),"该用户已经被注册");
        //校验当前手机号码是否已经被注册
        AssertUtils.handlerCheck(!(userInfoMapper.getByPhone(phone)==null),"该手机号码已经被注册");
        //设置当前 用户的状态
        login.setStatus(UserLogin.STATU_IN_NOMAL);
        login.setPassword(PasswordUtil.MD5(login.getPassword(),login.getUsername()));
        loginMapper.insert(login);
        //保存当前用户真实信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(login.getId());
        userInfo.setPhone(phone);
        userInfo.setRegistTime(new Date());
        userInfoMapper.insert(userInfo);
    }


    public String login(UserLogin login) {
        //判读当前用户是否存在
        String msg = "用户名或者密码错误";
        UserLogin userLogin = loginMapper.getByUsername(login.getUsername());
        AssertUtils.isNull(userLogin,msg);
        AssertUtils.handlerCheck(!(userLogin.getPassword().equals(
                PasswordUtil.MD5(login.getPassword(),login.getUsername()))),msg);
        //查询出当前真是用户
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userLogin.getId());
        //生成token令牌
        String token = createToken();
        //获取token的前缀
        String keyToken =  UserKeyPrefix.USER_KEY_PREFIX.getRealKey(token);
        //将数据缓存到redis中
        redisServiceImpl.set(keyToken,userInfo,3);
        //将token返回给前端接口
        return token;
    }

    @Override
    public UserInfo getUserByToken(String token) {
        String realKey = UserKeyPrefix.USER_KEY_PREFIX.getRealKey(token);
        return redisServiceImpl.get(realKey);
    }

    @Override
    public UserAddress getAddressById(Long userAddressId) {
        return addressMapper.selectByPrimaryKey(userAddressId);
    }

    @Override
    public void logout(String token) {
        String key = UserKeyPrefix.USER_KEY_PREFIX.getRealKey(token);
        Object o = redisServiceImpl.get(key);
        if(o == null){
            throw new WSException("登录后才能进行注销操作");
        }
        redisServiceImpl.remove(key);
    }

    /**
     * 创建token的方法
     * @return
     */
    private String createToken() {
        return UUID.randomUUID().toString().replace("-","").trim();
    }
}

package cn.wolfcode.shop.redisUtil;

public class UserKeyPrefix extends AbstractKeyPrefix{

    public static final UserKeyPrefix USER_KEY_PREFIX = new UserKeyPrefix();

    private UserKeyPrefix(){}

    @Override
    public String getPrefix() {
        return this.getClass().getSimpleName()+USERKEY_PREFIX+":";
    }

    @Override
    public String getRealKey(String token){
        return getPrefix()+token;
    }

}

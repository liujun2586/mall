package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.UserLogin;
import java.util.List;

public interface UserLoginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLogin record);

    UserLogin selectByPrimaryKey(Long id);

    List<UserLogin> selectAll();

    int updateByPrimaryKey(UserLogin record);

    /**
     * 根据用户名查询当前用户名字是否存在
     * @param username
     * @return
     */
    UserLogin getByUsername(String username);
}
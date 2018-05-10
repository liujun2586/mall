package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Car record);

    Car selectByPrimaryKey(Long id);

    List<Car> selectAll();

    int updateByPrimaryKey(Car record);

    /**
     * 根据用户Id获取购物车对象
     * @param userId
     * @return
     */
    Car getByUserId(@Param("userId") Long userId, @Param("skuId")Long skuId);

}
package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Car;

/**
 * 购物车相关接口
 */
public interface IShoppingCarService {
    /**
     * 添加到购物车
     * @param productNums
     * @param skusn
     * @param token
     */
    void addCar(Integer productNums, String skusn, String token);

    void deleteById(Long id);

    Car getById(Long id);
}

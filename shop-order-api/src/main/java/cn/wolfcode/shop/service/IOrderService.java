package cn.wolfcode.shop.service;

import cn.wolfcode.shop.exception.WSException;
import cn.wolfcode.shop.vo.GenOrderVo;

/**
 * 操作订单相关接口
 */
public interface IOrderService {
    /**
     * 生成订单对象
     * @param vo
     */
    void genOrder(GenOrderVo vo) throws WSException;
}

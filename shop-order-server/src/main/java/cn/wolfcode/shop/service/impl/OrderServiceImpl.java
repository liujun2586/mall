package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.exception.WSException;
import cn.wolfcode.shop.mapper.OrderActionMapper;
import cn.wolfcode.shop.mapper.OrderInfoMapper;
import cn.wolfcode.shop.mapper.OrderProductMapper;
import cn.wolfcode.shop.mapper.OrderProductPropertyMapper;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.util.AssertUtils;
import cn.wolfcode.shop.util.IdGenerateUtil;
import cn.wolfcode.shop.vo.GenOrderVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Reference
    private IUserService userService;
    @Reference
    private IProductSkuService productSkuService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private IInvoiceService iInvoiceService;
    @Autowired
    private OrderActionMapper orderActionMapper;
    @Reference
    private ISkuPropertyValueService skuPropertyValueService;
    @Autowired
    private OrderProductPropertyMapper orderProductPropertyMapper;
    @Reference
    private IShoppingCarService shoppingCarService;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void genOrder(GenOrderVo vo) throws WSException {
        //查询出当前登录的用户
        UserInfo userInfo = userService.getUserByToken(vo.getToken());
        //初始化订单编号
        long orderSn = IdGenerateUtil.get().nextId();
        UserAddress address = userService.getAddressById(vo.getUserAddressId());
        boolean b = address.getUserId().equals(userInfo.getId());
        AssertUtils.handlerCheck(!b, "非法操作!");
        //判断当前发货地址的用户和当前登录用户是否为同一人
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(address, orderInfo);
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setOrderStatus(0);
        orderInfo.setOrderTime(new Date());
        orderInfo.setFlowStatus(0);
        orderInfo.setPayType(vo.getPay().getPayType());
        orderInfo.setPayStatus(0);
        orderInfo.setOrderSn(orderSn + "");
        //保存基础订单对象
        orderInfoMapper.insert(orderInfo);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Car car : vo.getCarList()) {
            Car carInDB = shoppingCarService.getById(car.getId());
            AssertUtils.isNull(carInDB,"购物车不能为空");
            Long skuId = carInDB.getSkuId();
            ProductSku sku = productSkuService.getById(skuId);
            totalAmount = totalAmount.add(sku.getPrice().multiply(new BigDecimal(carInDB.getProductNumber()+"")).setScale(2, RoundingMode.HALF_UP));
            orderInfo.setOrderAmount(orderInfo.getOrderAmount().add(totalAmount));
            //保存商品订单对象
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setSkuId(sku.getId());
            orderProduct.setProductName(carInDB.getProductName());
            orderProduct.setProductPrice(sku.getPrice());
            orderProduct.setTotalPrice(totalAmount);
            orderProduct.setProductNumber(carInDB.getProductNumber());
            orderProduct.setOrderId(orderInfo.getId());
            orderProductMapper.insert(orderProduct);
            List<ProductSkuProperty> skuPropertyList = sku.getProductSkuPropertyList();
            for (ProductSkuProperty productSkuProperty : skuPropertyList) {
                SkuProperty skuProperty = productSkuProperty.getSkuProperty();
                OrderProductProperty orderProductProperty = new OrderProductProperty();
                orderProductProperty.setName(skuProperty.getName());
                orderProductProperty.setValue(productSkuProperty.getValue());
                orderProductProperty.setProductId(sku.getProductId());
                //保存订单商品属性对象
                orderProductPropertyMapper.insert(orderProductProperty);
            }
        }
        //修改订单对象
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        //保存发票对象
        Invoice invoice = vo.getInvoice();
        invoice.setUserId(userInfo.getId());
        invoice.setOrderId(orderInfo.getId());
        iInvoiceService.save(invoice);
        //删除购物车
        vo.getCarList().forEach(car -> {
            shoppingCarService.deleteById(car.getId());
        });
    }
}

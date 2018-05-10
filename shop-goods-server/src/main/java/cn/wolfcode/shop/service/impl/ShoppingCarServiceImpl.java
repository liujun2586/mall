package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.mapper.CarMapper;
import cn.wolfcode.shop.service.*;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ShoppingCarServiceImpl implements IShoppingCarService{
    @Autowired
    private CarMapper carMapper;
    @Reference
    private IUserService service;
    @Autowired
    private IProductSkuService productSkuService;
    @Autowired
    private IProductService productService;

    @Override
    public void addCar(Integer productNums, String skusn, String token) {
        //判断当前用户是否已经存在相同的购物商品
        UserInfo userInfo = service.getUserByToken(token);
        ProductSku sku = productSkuService.getBySn(skusn);
        Car carInDB  =  carMapper.getByUserId(userInfo.getId(),sku.getId());

        if(carInDB !=null){
            //进行修改操作
            carInDB.setProductNumber(carInDB.getProductNumber()+productNums);
            carMapper.updateByPrimaryKey(carInDB);
        }else{
            //进行新增操作
            Car car = new Car();
            car.setProductNumber(productNums);
            Product product = productService.getProductById(sku.getProductId());
            car.setProductName(product.getName());
            car.setSkuId(sku.getId());
            car.setUserId(userInfo.getId());
            ProductSkuProperty property = sku.getProductSkuPropertyList().get(0);
            car.setProductImg(property.getImage());
            carMapper.insert(car);
        }
    }

    @Override
    public void deleteById(Long id) {
        carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Car getById(Long id) {
        return carMapper.selectByPrimaryKey(id);
    }
}

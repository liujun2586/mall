package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.vo.GenerateVO;
import cn.wolfcode.shop.vo.ProductSkuVo;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productSku")
public class ProductSkuController {

    @Reference
    private IProductService productService;
    @Reference
    private ISkuPropertyService skuPropertyService;
    @Reference
    private ISkuPropertyValueService skuPropertyValueService;
    @Reference
    private IProductSkuService productSkuService;
    @Reference
    private IProductSkuPropertyService productSkuPropertyService;


    @RequestMapping
    public String skuList(Long productId,Map map){
        //查询当前商品
        Product product = productService.getProductById(productId);
        List<SkuProperty> skuPropertys =null;
        List<ProductSku> productSkuList = productSkuService.getByProductId(productId);
        map.put("product",product);
        if(!CollectionUtils.isEmpty(productSkuList)){
            map.put("skuList",productSkuList);
            map.put("skuPropertyList",skuPropertyService.queryByProductId(productId));
            return "product_sku/sku_list";
        }

        skuPropertys = skuPropertyService.getByCataLogId(product.getCatalog().getId());
        map.put("skuPropertyList",skuPropertys);
        return "product_sku/generate_sku";
    }

    /**
     * 所有的sku属性值接口界面
     * @param skuPropertyId
     * @param map
     * @return
     */
    @RequestMapping("getSkuPropertyValue")
    public String getSkuPropertyValue(Long skuPropertyId,Map map){
        SkuProperty skuProperty =  skuPropertyService.getById(skuPropertyId);
        List<SkuPropertyValue> skuPropertyValue = skuPropertyValueService.getBySkuPropertyId(skuPropertyId);
        map.put("skuPropertyValueList",skuPropertyValue);
        map.put("skuProperty",skuProperty);
        return "product_sku/sku_property_value_table";
    }

    /**
     * 生成sku的接口
     * @param vo
     * @return
     */
    @RequestMapping("generateSku")
    public String generateSku(@RequestBody GenerateVO vo, Map map){
        List<Map<String,Object>> skuList =  productSkuService.generateSku(vo);
        map.put("skuProList",vo.getSkuPropertyList());
        map.put("skuList",skuList);
        return "product_sku/sku_form";
    }

    @PostMapping("/save")
    @ResponseBody
    public WSResponseVo save(ProductSkuVo vo){
        productSkuService.save(vo);
        return new WSResponseVo();
    }
}

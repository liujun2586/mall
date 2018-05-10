package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.service.ISkuPropertyValueService;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 属性值相关
 */
@Controller
@RequestMapping("/skuPropertyValue")
public class SkuPropertyValueController {
    @Reference
    private ISkuPropertyValueService skuPropertyValueService;
    @Reference
    private ISkuPropertyService skuPropertyService;
    @RequestMapping
    public String list(Long propertyId,Map map){
      List<SkuPropertyValue> skuPropertyValueList =   skuPropertyValueService.getBySkuPropertyId(propertyId);
      map.put("skuPropertyValueList",skuPropertyValueList);
      map.put("skuPropertyId",propertyId);
      return "skuProperty/skuProperty_value_list";
    }

    @PostMapping
    @ResponseBody
    public WSResponseVo saveOrUpdate(@RequestBody List<SkuPropertyValue> values){
        skuPropertyValueService.batchUpdate(values);
        return new WSResponseVo();
    }

    @GetMapping("{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Long id){
        skuPropertyValueService.delete(id);
    }
}

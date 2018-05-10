package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.service.IPropertyService;
import cn.wolfcode.shop.service.IPropertyValueService;
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
@RequestMapping("/propertyValue")
public class PropertyValueController {
    @Reference
    private IPropertyValueService propertyValueService;
    @Reference
    private IPropertyService propertyService;
    @RequestMapping
    public String list(Long propertyId,Map map){
      List<PropertyValue> propertyValueList =   propertyValueService.getByPropertyId(propertyId);
      map.put("propertyValueList",propertyValueList);
      map.put("propertyId",propertyId);
      return "property/property_value_list";
    }

    @PostMapping
    @ResponseBody
    public WSResponseVo saveOrUpdate(@RequestBody List<PropertyValue> values){
        propertyValueService.batchUpdate(values);
        return new WSResponseVo();
    }

    @GetMapping("{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Long id){
        propertyValueService.delete(id);
    }
}

package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.service.IPropertyService;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 属性界面相关接口
 */
@Controller
@RequestMapping("properties")
public class PropertyController {

    @Reference
    private IPropertyService propertyService;

    @GetMapping
    public String index(){
        return "property/property";
    }

    @RequestMapping("list")
    public String list(Long catalogId,Map map){
        map.put("properties",propertyService.getByCataLogId(catalogId));
        return "property/property_list";
    }
    @PostMapping
    @ResponseBody
    public WSResponseVo saveOrUpdate(Property property){
        propertyService.saveOrUpdate(property);
        return new WSResponseVo();
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public WSResponseVo delete(@PathVariable("id")Long id){
        propertyService.delete(id);
        return new WSResponseVo();
    }
}

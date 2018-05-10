package cn.wolfcode.shop.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter@Getter
public class SkuProperty extends BaseDomain {

    /**
     * 输入框
     */
    public static final Integer PROPERTY_TYPE_INPUT = 0;
    /**
     * 下拉框
     */
    public static final Integer PROPERTY_TYPE_SELECT = 1;

    //目录id
    private Long catalogId;
    //属性名称
    private String name;
    //排序编号
    private Integer sort;
    //属性类型
    private Integer type;
    //属性值
    private List<PropertyValue> valueList = new ArrayList<>();

    @JSONField(serialize = false)
    public String getJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("catalogId",catalogId);
        map.put("name",name);
        map.put("sort",sort);
        map.put("type",type);
        return JSON.toJSONString(map);
    }

}
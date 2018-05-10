package cn.wolfcode.shop.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 目录相关对象
 */
@Setter@Getter
public class Catalog extends BaseDomain{

    private String name; //名称

    private String sn;//编码

    private Integer sort;//排序编号

    private Long parentId;//上级目录id

    private Boolean isParent;//是否是父级目录

    /**
     * 用来给界面的展示数据
     * @return
     */
    @JSONField(serialize = false)
    public String getJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("name",name);
        map.put("sn",sn);
        map.put("sort",sort);
        map.put("parentId",parentId);
        map.put("isParent",isParent);
        return JSON.toJSONString(map);
    }
}
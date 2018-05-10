package cn.wolfcode.shop.vo;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.domain.SkuPropertyValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 接收生成sku时需要保存的数据
 */
@Setter@Getter
public class GenerateVO implements Serializable {
    private Product product;

    private List<SkuProperty> skuPropertyList = new ArrayList<>();

    private List<SkuPropertyValue> skuPropertyValueList = new ArrayList<>();
}

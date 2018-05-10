package cn.wolfcode.shop.vo;


import cn.wolfcode.shop.domain.Catalog;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品的属性数量 和上屏个数
 */
@Setter@Getter
public class CatalogVO extends Catalog {
    //商品数量
    private Integer productNum;
    //属性数量
    private Integer propertyNum;
}

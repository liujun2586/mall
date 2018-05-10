package cn.wolfcode.shop.vo;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.domain.ProductSku;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class ProductSkuVo implements Serializable {

    private Product product;

    private List<ProductSku> ProductSkuList = new ArrayList<>();
}

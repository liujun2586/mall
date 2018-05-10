package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.mapper.ProductSkuMapper;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.IProductSkuPropertyService;
import cn.wolfcode.shop.service.IProductSkuService;
import cn.wolfcode.shop.vo.GenerateVO;
import cn.wolfcode.shop.vo.ProductSkuVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class ProductSkuServiceImpl implements IProductSkuService {

    @Autowired
    private ICatalogService catalogService;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private IProductSkuPropertyService productSkuPropertyService;

    @Override
    public List<Map<String,Object>> generateSku(GenerateVO generateVo) {
        Product product = generateVo.getProduct();
        List<SkuProperty> skuPropertyList = generateVo.getSkuPropertyList();
        List<SkuPropertyValue> skuPropertyValueList = generateVo.getSkuPropertyValueList();
        //生成sku的编号
        String SkuSn = this.generateSkuSn(product);
        //将传入的数据转换成生成sku的格式
        List<List<SkuPropertyValue>> datas = this.convertData(skuPropertyList, skuPropertyValueList);
        //生成sku
        List<List<SkuPropertyValue>> skuList = this.BuildSkus(datas);
        //List<List<SkuPropertyValue>> skuList = DescartesUtils.descart(datas);
        List<Map<String,Object>> resMap = new LinkedList<>();
        for (int i = 0; i <skuList.size() ; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("sn",SkuSn+(i+1));
            map.put("price",product.getBasePrice());
            List<SkuPropertyValue> values = skuList.get(i);
            values.forEach(value ->{
                map.put(value.getSkuPropertyId()+"",value.getValue());
            });
            resMap.add(map);
        }
        return resMap;
    }

    @Override
    public void save(ProductSkuVo vo) {
        //保存sku信息
        List<ProductSku> skuList = vo.getProductSkuList();
        skuList.forEach(sku ->{
            sku.setProductId(vo.getProduct().getId());
            productSkuMapper.insert(sku);
            List<ProductSkuProperty> skuPropertyList = sku.getProductSkuPropertyList();
            skuPropertyList.forEach(skuProperty ->{
                skuProperty.setProductSkuId(sku.getId());
                productSkuPropertyService.insert(skuProperty);
            });
        });
    }

    @Override
    public List<ProductSku> getByProductId(Long productId) {
        return productSkuMapper.getByProductId(productId);
    }

    @Override
    public ProductSku getBySn(String sn) {
        return productSkuMapper.getBySn(sn);
    }

    @Override
    public ProductSku getById(Long id) {

        return productSkuMapper.selectByPrimaryKey(id);
    }

    /**
     * 生成sku的方法
     *
     * @param datas
     * @return
     */
    private List<List<SkuPropertyValue>> BuildSkus(List<List<SkuPropertyValue>> datas) {
        List<List<SkuPropertyValue>> top = new ArrayList<>();
        Queue<List<SkuPropertyValue>> queue = new PriorityQueue<>();

        for (int i = 0; i < datas.size(); i++) {
            List<SkuPropertyValue> values = datas.get(i);
            if (i == 0) {
                for (SkuPropertyValue value : values) {
                    List<SkuPropertyValue> tem = new LinkedList<>();
                    tem.add(value);
                    top.add(tem);
                }

            } else {
                queue.add(datas.get(i));
            }
        }

        //迭代queque的第一个
        List<SkuPropertyValue> first = queue.poll();
        while (first != null) {
            //创建临时的集合用来存放top
            List<List<SkuPropertyValue>> tmpTop = new LinkedList<>();
            //迭代所有队列中的元素
            for (SkuPropertyValue skuPropertyValue : first) {
                for (int i = 0; i < top.size(); i++) {
                    List<SkuPropertyValue> tempSk = new LinkedList<>(top.get(i));
                    tempSk.add(skuPropertyValue);
                    tmpTop.add(tempSk);
                }
            }

            //将临时的top赋值给top
            top = tmpTop;
            //将first修改
            first = queue.poll();
        }
        return top;
    }

    /**
     * 将输出转换成生成sku的格式
     *
     * @param skuPropertyList
     * @param skuPropertyValueList
     * @return
     */
    private List<List<SkuPropertyValue>> convertData(List<SkuProperty> skuPropertyList, List<SkuPropertyValue> skuPropertyValueList) {
        List<List<SkuPropertyValue>> datas = new ArrayList<>();
        skuPropertyList.forEach(property -> {
            List<SkuPropertyValue> data = new ArrayList<>();
            datas.add(data);

            skuPropertyValueList.forEach(value -> {
                if (value.getSkuPropertyId().equals(property.getId())) {
                    data.add(value);
                }
            });
        });

        return datas;
    }

    /**
     * 生成sku编号
     *
     * @param product
     * @return
     */
    private String generateSkuSn(Product product) {
        StringBuilder sb = new StringBuilder(50);
        //根据当前商品获取所有的上一级目录
        List<Catalog> catalogs = catalogService.getParentsById(product.getCatalog().getId());
        if (!CollectionUtils.isEmpty(catalogs)) {
            for (int i = 0; i < catalogs.size(); i++) {
                Catalog catalog = catalogs.get(i);
                if (i == 0) {
                    sb.append(catalog.getSn().substring(0, 2).toUpperCase());
                } else {
                    Integer sort = catalog.getSort();
                    String sn = sort < 10 ? "0" + sort : sort + "";
                    sb.append(sn);
                }
            }
        }
        return sb.append(product.getId()).toString();
    }
}

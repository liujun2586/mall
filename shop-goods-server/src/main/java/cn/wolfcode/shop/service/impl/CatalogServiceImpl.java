package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.exception.WSException;
import cn.wolfcode.shop.mapper.CatalogMapper;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.vo.CatalogVO;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CatalogServiceImpl implements ICatalogService {

    @Autowired
    private CatalogMapper mapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Catalog> listAll() {
        return mapper.selectAll();
    }

    @Override
    public List<CatalogVO> getByParentId(Long parentId) {
        List<Catalog> catalogs = mapper.getById(parentId);
        LinkedList<CatalogVO> vos = new LinkedList<>();
        catalogs.forEach(catalog -> {
            String key = "catalog:" + catalog.getId();
            //获取redis中存在的个数
            HashOperations<String, String, Integer> opsForHash = redisTemplate.opsForHash();
            Map<String, Integer> entries = opsForHash.entries(key);
            if (entries == null || entries.size() == 0) {
                Map<String, Integer> map = new HashMap<>();
                //在数据库中进行查询操作
                Integer productNums  = productNums = mapper.selProductNum(catalog.getId());
                Integer propertyNums =propertyNums = mapper.selPropertyNum(catalog.getId());
                map.put("productNums", productNums);
                map.put("propertyNums", propertyNums);
                //将查询出来的数据缓存到redis中
                opsForHash.putAll(key,map);
            }
            CatalogVO vo = new CatalogVO();
            BeanUtils.copyProperties(catalog,vo);
            vo.setProductNum(entries.get("productNums"));
            vo.setPropertyNum(entries.get("propertyNums"));
            vos.add(vo);
        });
        return vos;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Catalog catalog) throws WSException {
        if (catalog.getId() == null) {
            //查询出当前统计目录的sort 设置自增1
            int sort = mapper.maxSort(catalog);
            catalog.setSort(sort+1);
            //如果当前是进行新增操作
            if (catalog.getParentId() != null) {
                //如果当前进行的不是保存顶级目录,修改当前目录的父级目录为是父级目录
                int count = mapper.getChildrenCountByParentId(catalog.getParentId());
                if (count == 0) {
                    Catalog parent = mapper.selectByPrimaryKey(catalog.getParentId());
                    parent.setIsParent(true);
                    mapper.updateByPrimaryKey(parent);
                }
            } else {
                //查询当前同级目录的最大sort编号
                catalog.setIsParent(true);
            }
            //保存操作
            mapper.insert(catalog);
        } else {
            mapper.updateByPrimaryKey(catalog);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        //当前删除的目录是否存在其他的子目录,如果不存在修改当前父级目录为false
        Catalog parent = mapper.getParentByChildrenId(id);
        if (parent != null) {
            int count = mapper.getChildrenCountByParentId(parent.getId());
            if (count == 1) {
                parent.setIsParent(false);
                mapper.updateByPrimaryKey(parent);
            }
        }
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void sortCatalog(Long[] ids) {
        //根据传入的id进行排序操作
        for (int i = 0; i < ids.length; i++) {
            mapper.updateSort(ids[i], i + 1);
        }
    }

    @Override
    public List<Long> selectAllCatalogId() {
        return mapper.selectAllCatalogId();
    }

    @Override
    public Integer selProductNum(Long catalogId) {
        return mapper.selProductNum(catalogId);
    }

    @Override
    public Integer selPropertyNum(Long catalogId) {
        return mapper.selPropertyNum(catalogId);
    }

    @Override
    public List<Catalog> getParentsById(Long id) {
        return mapper.getParentsById(id);
    }

}

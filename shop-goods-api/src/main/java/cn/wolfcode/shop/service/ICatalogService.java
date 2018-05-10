package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.exception.WSException;
import cn.wolfcode.shop.vo.CatalogVO;

import java.util.List;

/**
 * 商品目录相关接口
 */
public interface ICatalogService {
    /**
     * 查询所有目录
     * @return 所有目录
     */
    List<Catalog> listAll();

    /**
     * 根据父类查询所有的结构
     * @param parentId
     * @return
     */
    List<CatalogVO> getByParentId(Long parentId);

    /**
     * 更新目录
     * @param catalog
     */
    void saveOrUpdate(Catalog catalog) throws WSException;

    /**
     * 删除目录接口
     * @param id
     */
    void delete(Long id);

    /**
     * 实现排序分类的接口
     * @param ids
     */
    void sortCatalog(Long[] ids);

    /**
     * 查询所有的目录id
     * @return
     */
    List<Long> selectAllCatalogId();

    /**
     * 根据目录id查询商品总数
     * @param catalogId
     * @return
     */
    Integer selProductNum(Long catalogId);

    /**
     * 根据目录id查询属性总数
     * @param catalogId
     * @return
     */
    Integer selPropertyNum(Long catalogId);

    /**
     *  根据id查询所有的父级目录
     * @param id
     * @return
     */
    List<Catalog> getParentsById(Long id);
}

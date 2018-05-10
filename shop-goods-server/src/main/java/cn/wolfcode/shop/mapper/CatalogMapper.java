package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.Catalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品目录相关mapper
 */
public interface CatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    Catalog selectByPrimaryKey(Long id);

    List<Catalog> selectAll();

    int updateByPrimaryKey(Catalog record);

    /**
     * 根据父级id查询所有目录
     * @param id
     * @return
     */
    List<Catalog> getById(@Param("id") Long id);

    /**
     * 根据父级目录查询子目录的统计个数
     * @param parentId
     * @return
     */
    int getChildrenCountByParentId(Long parentId);

    /**
     * 根据子目录获取父级目录
     * @param id
     * @return
     */
    Catalog getParentByChildrenId(Long id);

    /**
     * 修改传入的id的排序号码
     * @param id
     * @param sortNum
     */
    void updateSort(@Param("id")Long id,@Param("sortNum")int sortNum);

    /**
     * 根据目录id查询商品总数
     * @param catalogId
     * @return
     */
    int selProductNum(Long catalogId);

    /**
     * 根据目录id查询属性总数
     * @param catalogId
     * @return
     */
    int selPropertyNum(Long catalogId);

    /**
     * 查询所有的商品分类id
     * @return
     */
    List<Long> selectAllCatalogId();

    /**
     * 查询当前同级目录最大的sort编号
     * @param catalog
     * @return
     */
    int maxSort(Catalog catalog);

    /**
     * 根据当前id查询所有父级目录
     * @param id
     * @return
     */
    List<Catalog> getParentsById(Long id);
}
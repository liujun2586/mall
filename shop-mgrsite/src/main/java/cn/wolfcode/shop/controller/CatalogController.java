package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.vo.CatalogVO;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品目录相关界面接口
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Reference
    private ICatalogService catalogService;

    /**
     * 商品分类根界面
     *
     * @return
     */
    @GetMapping
    public String catalog() {
        return "/catalog/catalog";
    }

    /**
     * 加载所有的目录结构
     */

    @GetMapping("/children")
    @ResponseBody
    public List<CatalogVO> childredList(Long id) {
        return catalogService.getByParentId(id);
    }

    /**
     * 加载列表界面
     *
     * @param id
     * @param map
     * @return
     */
    @GetMapping(value = "/children", produces = "text/html")
    public String children4Html(Long id, Map map) {
        map.put("catalogList", catalogService.getByParentId(id));
        return "catalog/children";
    }

    /**
     * 添加或者保存目录
     *
     * @return
     */
    @PostMapping
    @ResponseBody
    public WSResponseVo saveOrUpdate(Catalog catalog) {
        catalogService.saveOrUpdate(catalog);
        return new WSResponseVo();
    }

    /**
     * 删除目录接口
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public WSResponseVo delete(@PathVariable("id") Long id) {
        catalogService.delete(id);
        return new WSResponseVo();
    }

    /**
     * 重新排序目录列表的接口
     *
     * @param ids
     * @return
     */
    @PatchMapping(produces = "application/json")
    @ResponseBody
    public WSResponseVo sortCatalog(@RequestBody Long[] ids) {
        catalogService.sortCatalog(ids);
        return new WSResponseVo();
    }
}

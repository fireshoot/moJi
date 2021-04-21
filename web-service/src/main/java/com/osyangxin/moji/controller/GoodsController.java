package com.osyangxin.moji.controller;

import com.osyangxin.dao.model.ProductInfo;
import com.osyangxin.moji.bean.ResultPageView;
import com.osyangxin.moji.model.vo.Product;
import com.osyangxin.moji.model.vo.TbPanel;
import com.osyangxin.moji.service.ContentService;
import com.osyangxin.moji.service.SearchService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Exrickx
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private SearchService searchService;

    @Resource
    private ContentService contentService;

 /*   @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/goods/navList",method = RequestMethod.GET)
    @ApiOperation(value = "获取导航栏")
    public Result<List<TbPanelContent>> getNavList(){

        List<TbPanelContent> list=contentService.getNavList();
        return new ResultUtil<List<TbPanelContent>>().setData(list);
    }

    @RequestMapping(value = "/goods/home",method = RequestMethod.GET)
    @ApiOperation(value = "首页内容展示")
    public Result<List<TbPanel>> getProductHome(){

        List<TbPanel> list=contentService.getHome();
        return new ResultUtil<List<TbPanel>>().setData(list);
    }

    @RequestMapping(value = "/goods/productDet",method = RequestMethod.GET)
    @ApiOperation(value = "商品详情")
    public Result<ProductDet> getProductDet(Long productId){

        ProductDet productDet=contentService.getProductDet(productId);
        return new ResultUtil<ProductDet>().setData(productDet);
    }*/

    @GetMapping(value = "/allGoods")
    public ResultPageView<ProductInfo> getAllProduct(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "20") int size,
                                                     String sort,
                                                     Long cid,
                                                     Integer priceGt,
                                                     Integer priceLte) {
        return contentService.getAllProduct(page, size, sort, cid, priceGt, priceLte);
    }

    @GetMapping(value = "/home")
    public List<TbPanel> getProductHome() {
        return contentService.getHome();
    }

  /*  @RequestMapping(value = "/goods/search",method = RequestMethod.GET)
    @ApiOperation(value = "搜索商品ES")
    public Result<SearchResult> searchProduct(@RequestParam(defaultValue = "") String key,
                                              @RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "20") int size,
                                              @RequestParam(defaultValue = "") String sort,
                                              @RequestParam(defaultValue = "-1") int priceGt,
                                              @RequestParam(defaultValue = "-1") int priceLte){

        SearchResult searchResult=searchService.search(key,page,size,sort,priceGt,priceLte);
        return new ResultUtil<SearchResult>().setData(searchResult);
    }

    @RequestMapping(value = "/goods/recommend",method = RequestMethod.GET)
    @ApiOperation(value = "商品推荐板块")
    public Result<List<TbPanel>> getRecommendGoods(){

        List<TbPanel> list=contentService.getRecommendGoods();
        return new ResultUtil<List<TbPanel>>().setData(list);
    }

    @RequestMapping(value = "/goods/thank",method = RequestMethod.GET)
    @ApiOperation(value = "我要捐赠板块")
    public Result<List<TbPanel>> getThankGoods(){

        List<TbPanel> list=contentService.getThankGoods();
        return new ResultUtil<List<TbPanel>>().setData(list);
    }

    @RequestMapping(value = "/goods/quickSearch",produces= "text/plain;charset=UTF-8",method = RequestMethod.GET)
    @ApiOperation(value = "快速搜索")
    public String getQuickSearch(@RequestParam(defaultValue = "") String key){

        return searchService.quickSearch(key);
    }*/
}

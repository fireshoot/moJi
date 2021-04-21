package com.osyangxin.moji.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.osyangxin.dao.mapper.ProductInfoMapper;
import com.osyangxin.dao.model.ProductInfo;
import com.osyangxin.dao.req.ProductQueryCondition;
import com.osyangxin.moji.annotation.PageAble;
import com.osyangxin.moji.bean.ResultPageView;
import com.osyangxin.moji.model.vo.Product;
import com.osyangxin.moji.model.vo.TbPanel;
import com.osyangxin.moji.model.vo.TbPanelContent;
import com.osyangxin.moji.service.ContentService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private ProductInfoMapper productInfoMapper;

//    @Autowired
//    private TbPanelMapper tbPanelMapper;
//    @Autowired
//    private TbPanelContentMapper tbPanelContentMapper;
//    @Autowired
//    private TbItemMapper tbItemMapper;
//    @Autowired
//    private TbItemDescMapper tbItemDescMapper;
//    @Autowired
//    private JedisClient jedisClient;
//
//    @Value("${PRODUCT_HOME}")
//    private String PRODUCT_HOME;
//
//    @Value("${PRODUCT_ITEM}")
//    private String PRODUCT_ITEM;
//
//    @Value("${RECOMEED_PANEL_ID}")
//    private Integer RECOMEED_PANEL_ID;
//
//    @Value("${THANK_PANEL_ID}")
//    private Integer THANK_PANEL_ID;
//
//    @Value("${RECOMEED_PANEL}")
//    private String RECOMEED_PANEL;
//
//    @Value("${THANK_PANEL}")
//    private String THANK_PANEL;
//
//    @Value("${ITEM_EXPIRE}")
//    private int ITEM_EXPIRE;
//
//    @Value("${HEADER_PANEL_ID}")
//    private int HEADER_PANEL_ID;
//
//    @Value("${HEADER_PANEL}")
//    private String HEADER_PANEL;

   /* @Override
    public int addPanelContent(TbPanelContent tbPanelContent) {

        tbPanelContent.setCreated(new Date());
        tbPanelContent.setUpdated(new Date());
        if(tbPanelContentMapper.insert(tbPanelContent)!=1){
            throw new XmallException("添加首页板块内容失败");
        }
        //同步导航栏缓存
        if(tbPanelContent.getPanelId()==HEADER_PANEL_ID){
            updateNavListRedis();
        }
        //同步缓存
        deleteHomeRedis();
        return 1;
    }

    @Override
    public DataTablesResult getPanelContentListByPanelId(int panelId) {

        DataTablesResult result=new DataTablesResult();
        List<TbPanelContent> list=new ArrayList<>();

        TbPanelContentExample example=new TbPanelContentExample();
        TbPanelContentExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andPanelIdEqualTo(panelId);
        list=tbPanelContentMapper.selectByExample(example);
        for(TbPanelContent content:list){
            if(content.getProductId()!=null){
                TbItem tbItem=tbItemMapper.selectByPrimaryKey(content.getProductId());
                content.setProductName(tbItem.getTitle());
                content.setSalePrice(tbItem.getPrice());
                content.setSubTitle(tbItem.getSellPoint());
            }
        }

        result.setData(list);
        return result;
    }

    @Override
    public int deletePanelContent(int id) {

        if(tbPanelContentMapper.deleteByPrimaryKey(id)!=1){
            throw new XmallException("删除首页板块失败");
        }
        //同步导航栏缓存
        if(id==HEADER_PANEL_ID){
            updateNavListRedis();
        }
        //同步缓存
        deleteHomeRedis();
        return 1;
    }

    @Override
    public int updateContent(TbPanelContent tbPanelContent) {

        TbPanelContent old=getTbPanelContentById(tbPanelContent.getId());
        if(StringUtils.isBlank(tbPanelContent.getPicUrl())){
            tbPanelContent.setPicUrl(old.getPicUrl());
        }
        if(StringUtils.isBlank(tbPanelContent.getPicUrl2())){
            tbPanelContent.setPicUrl2(old.getPicUrl2());
        }
        if(StringUtils.isBlank(tbPanelContent.getPicUrl3())){
            tbPanelContent.setPicUrl3(old.getPicUrl3());
        }
        tbPanelContent.setCreated(old.getCreated());
        tbPanelContent.setUpdated(new Date());
        if(tbPanelContentMapper.updateByPrimaryKey(tbPanelContent)!=1){
            throw new XmallException("更新板块内容失败");
        }
        //同步导航栏缓存
        if(tbPanelContent.getPanelId()==HEADER_PANEL_ID){
            updateNavListRedis();
        }
        //同步缓存
        deleteHomeRedis();
        return 1;
    }

    @Override
    public TbPanelContent getTbPanelContentById(int id) {

        TbPanelContent tbPanelContent=tbPanelContentMapper.selectByPrimaryKey(id);
        if(tbPanelContent==null){
            throw new XmallException("通过id获取板块内容失败");
        }
        return tbPanelContent;
    }
*/

    @Override
    public List<TbPanel> getHome() {
        String data = "[\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 7,\n" +
                "\t\t\t\"name\": \"轮播图\",\n" +
                "\t\t\t\"type\": 0,\n" +
                "\t\t\t\"sortOrder\": 0,\n" +
                "\t\t\t\"position\": 0,\n" +
                "\t\t\t\"limitNum\": 5,\n" +
                "\t\t\t\"status\": 1,\n" +
                "\t\t\t\"remark\": \"\",\n" +
                "\t\t\t\"created\": 1523766787000,\n" +
                "\t\t\t\"updated\": 1523766787000,\n" +
                "\t\t\t\"panelContents\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 70,\n" +
                "\t\t\t\t\t\"panelId\": 7,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150635087972564,\n" +
                "\t\t\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://ooo.0o0.ooo/2019/09/30/CAZ6QrXPBoh5aIT.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": \"https://ooo.0o0.ooo/2019/09/30/9Y5MHc8sfhJLk3u.png\",\n" +
                "\t\t\t\t\t\"picUrl3\": \"https://ooo.0o0.ooo/2019/09/30/lLv8xp3IWqa7Oz6.png\",\n" +
                "\t\t\t\t\t\"created\": 1569839898000,\n" +
                "\t\t\t\t\t\"updated\": 1569843454000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
                "\t\t\t\t\t\"subTitle\": \"此仅为支付测试商品 拍下不会发货\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://ooo.0o0.ooo/2019/09/30/CAZ6QrXPBoh5aIT.png\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 33,\n" +
                "\t\t\t\t\t\"panelId\": 7,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432835,\n" +
                "\t\t\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://ooo.0o0.ooo/2018/11/04/5bdeba4028e90.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": \"https://ooo.0o0.ooo/2018/11/04/5bdebb109a29a.png\",\n" +
                "\t\t\t\t\t\"picUrl3\": \"https://ooo.0o0.ooo/2018/11/04/5bdeba6753403.png\",\n" +
                "\t\t\t\t\t\"created\": 1523970502000,\n" +
                "\t\t\t\t\t\"updated\": 1524192439000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"捐赠商品\",\n" +
                "\t\t\t\t\t\"subTitle\": \"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://ooo.0o0.ooo/2018/11/04/5bdeba4028e90.png\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 34,\n" +
                "\t\t\t\t\t\"panelId\": 7,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150635087972564,\n" +
                "\t\t\t\t\t\"sortOrder\": 3,\n" +
                "\t\t\t\t\t\"fullUrl\": null,\n" +
                "\t\t\t\t\t\"picUrl\": \"https://s1.ax1x.com/2018/05/19/Ccdiid.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": \"\",\n" +
                "\t\t\t\t\t\"picUrl3\": \"\",\n" +
                "\t\t\t\t\t\"created\": 1523970510000,\n" +
                "\t\t\t\t\t\"updated\": 1523970512000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
                "\t\t\t\t\t\"subTitle\": \"此仅为支付测试商品 拍下不会发货\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://s1.ax1x.com/2018/05/19/Ccdiid.png\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 8,\n" +
                "\t\t\t\"name\": \"活动版块\",\n" +
                "\t\t\t\"type\": 1,\n" +
                "\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\"position\": 0,\n" +
                "\t\t\t\"limitNum\": 4,\n" +
                "\t\t\t\"status\": 1,\n" +
                "\t\t\t\"remark\": \"\",\n" +
                "\t\t\t\"created\": 1523790300000,\n" +
                "\t\t\t\"updated\": 1523790300000,\n" +
                "\t\t\t\"panelContents\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 25,\n" +
                "\t\t\t\t\t\"panelId\": 8,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432835,\n" +
                "\t\t\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\t\t\"fullUrl\": \"https://www.smartisan.com/jianguo3-accessories\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/6/610400xinpinpeijian.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1523790463000,\n" +
                "\t\t\t\t\t\"updated\": 1524151234000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"捐赠商品\",\n" +
                "\t\t\t\t\t\"subTitle\": \"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/6/610400xinpinpeijian.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 26,\n" +
                "\t\t\t\t\t\"panelId\": 8,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150635087972564,\n" +
                "\t\t\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\t\t\"fullUrl\": \"https://www.smartisan.com/service/#/tradein\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/6/610400yijiuhuanxin.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1523790480000,\n" +
                "\t\t\t\t\t\"updated\": 1524151248000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
                "\t\t\t\t\t\"subTitle\": \"此仅为支付测试商品 拍下不会发货\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/6/610400yijiuhuanxin.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 27,\n" +
                "\t\t\t\t\t\"panelId\": 8,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432835,\n" +
                "\t\t\t\t\t\"sortOrder\": 3,\n" +
                "\t\t\t\t\t\"fullUrl\": \"https://www.smartisan.com/category?id=69\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/4/489673079577637073.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1523790504000,\n" +
                "\t\t\t\t\t\"updated\": 1524151261000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"捐赠商品\",\n" +
                "\t\t\t\t\t\"subTitle\": \"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/4/489673079577637073.png\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 28,\n" +
                "\t\t\t\t\t\"panelId\": 8,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150635087972564,\n" +
                "\t\t\t\t\t\"sortOrder\": 4,\n" +
                "\t\t\t\t\t\"fullUrl\": \"https://www.smartisan.com/\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/fe6ab43348a43152b4001b4454d206ac.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1523790538000,\n" +
                "\t\t\t\t\t\"updated\": 1524151273000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
                "\t\t\t\t\t\"subTitle\": \"此仅为支付测试商品 拍下不会发货\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/fe6ab43348a43152b4001b4454d206ac.jpg\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"name\": \"热门商品\",\n" +
                "\t\t\t\"type\": 2,\n" +
                "\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\"position\": 0,\n" +
                "\t\t\t\"limitNum\": 3,\n" +
                "\t\t\t\"status\": 1,\n" +
                "\t\t\t\"remark\": \"\",\n" +
                "\t\t\t\"created\": 1524066553000,\n" +
                "\t\t\t\"updated\": 1523790316000,\n" +
                "\t\t\t\"panelContents\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 22,\n" +
                "\t\t\t\t\t\"panelId\": 1,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150635087972564,\n" +
                "\t\t\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\t\t\"fullUrl\": null,\n" +
                "\t\t\t\t\t\"picUrl\": \"https://ooo.0o0.ooo/2018/07/13/5b48a7f468bf2.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508682391000,\n" +
                "\t\t\t\t\t\"updated\": 1508682391000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
                "\t\t\t\t\t\"subTitle\": \"此仅为支付测试商品 拍下不会发货\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://ooo.0o0.ooo/2018/07/13/5b48a7f468bf2.png\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 23,\n" +
                "\t\t\t\t\t\"panelId\": 1,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432835,\n" +
                "\t\t\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://ooo.0o0.ooo/2020/07/24/6BV9uTwaqModbYL.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508682400000,\n" +
                "\t\t\t\t\t\"updated\": 1523969975000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"捐赠商品\",\n" +
                "\t\t\t\t\t\"subTitle\": \"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://ooo.0o0.ooo/2020/07/24/6BV9uTwaqModbYL.png\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"name\": \"官方精选\",\n" +
                "\t\t\t\"type\": 3,\n" +
                "\t\t\t\"sortOrder\": 3,\n" +
                "\t\t\t\"position\": 0,\n" +
                "\t\t\t\"limitNum\": 8,\n" +
                "\t\t\t\"status\": 1,\n" +
                "\t\t\t\"remark\": \"\",\n" +
                "\t\t\t\"created\": null,\n" +
                "\t\t\t\"updated\": 1524108059000,\n" +
                "\t\t\t\"panelContents\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 29,\n" +
                "\t\t\t\t\t\"panelId\": 2,\n" +
                "\t\t\t\t\t\"type\": 2,\n" +
                "\t\t\t\t\t\"productId\": 150642571432843,\n" +
                "\t\t\t\t\t\"sortOrder\": 0,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/1/1220858shoujilouceng.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1523794475000,\n" +
                "\t\t\t\t\t\"updated\": 1524195687000,\n" +
                "\t\t\t\t\t\"salePrice\": 1999,\n" +
                "\t\t\t\t\t\"productName\": \"坚果 3\",\n" +
                "\t\t\t\t\t\"subTitle\": \"漂亮得不像实力派\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/1/1220858shoujilouceng.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 8,\n" +
                "\t\t\t\t\t\"panelId\": 2,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432837,\n" +
                "\t\t\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/3a7522290397a9444d7355298248f197.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1506330228000,\n" +
                "\t\t\t\t\t\"updated\": 1524151406000,\n" +
                "\t\t\t\t\t\"salePrice\": 49,\n" +
                "\t\t\t\t\t\"productName\": \"坚果 3 前屏钢化玻璃保护膜\",\n" +
                "\t\t\t\t\t\"subTitle\": \"超强透光率、耐刮花、防指纹\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/3a7522290397a9444d7355298248f197.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 9,\n" +
                "\t\t\t\t\t\"panelId\": 2,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432838,\n" +
                "\t\t\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/63ea40e5c64db1c6b1d480c48fe01272.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1506330275000,\n" +
                "\t\t\t\t\t\"updated\": 1524192497000,\n" +
                "\t\t\t\t\t\"salePrice\": 79,\n" +
                "\t\t\t\t\t\"productName\": \"坚果 3 绒布国旗保护套\",\n" +
                "\t\t\t\t\t\"subTitle\": \"质感精良、完美贴合、周到防护\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/63ea40e5c64db1c6b1d480c48fe01272.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 14,\n" +
                "\t\t\t\t\t\"panelId\": 2,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432839,\n" +
                "\t\t\t\t\t\"sortOrder\": 3,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/5e4b1feddb13639550849f12f6b2e202.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508681641000,\n" +
                "\t\t\t\t\t\"updated\": 1524192509000,\n" +
                "\t\t\t\t\t\"salePrice\": 29,\n" +
                "\t\t\t\t\t\"productName\": \"坚果 3 TPU 软胶透明保护套\",\n" +
                "\t\t\t\t\t\"subTitle\": \"轻薄透明、完美贴合、TPU 环保材质\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/5e4b1feddb13639550849f12f6b2e202.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 15,\n" +
                "\t\t\t\t\t\"panelId\": 2,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432840,\n" +
                "\t\t\t\t\t\"sortOrder\": 4,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/10525c4b21f039fc8ccb42cd1586f5cd.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508681692000,\n" +
                "\t\t\t\t\t\"updated\": 1524192523000,\n" +
                "\t\t\t\t\t\"salePrice\": 89,\n" +
                "\t\t\t\t\t\"productName\": \"Smartisan 半入耳式耳机\",\n" +
                "\t\t\t\t\t\"subTitle\": \"经典配色、专业调音、高品质麦克风\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/10525c4b21f039fc8ccb42cd1586f5cd.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 16,\n" +
                "\t\t\t\t\t\"panelId\": 2,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432841,\n" +
                "\t\t\t\t\t\"sortOrder\": 5,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/b899d9b82c4bc2710492a26af021d484.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508681751000,\n" +
                "\t\t\t\t\t\"updated\": 1524192542000,\n" +
                "\t\t\t\t\t\"salePrice\": 49,\n" +
                "\t\t\t\t\t\"productName\": \"坚果 3 TPU 软胶保护套\",\n" +
                "\t\t\t\t\t\"subTitle\": \"TPU 环保材质、完美贴合、周到防护\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/b899d9b82c4bc2710492a26af021d484.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 17,\n" +
                "\t\t\t\t\t\"panelId\": 2,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432842,\n" +
                "\t\t\t\t\t\"sortOrder\": 6,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/abb6986430536cd9365352b434f3c568.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508681821000,\n" +
                "\t\t\t\t\t\"updated\": 1524192557000,\n" +
                "\t\t\t\t\t\"salePrice\": 79,\n" +
                "\t\t\t\t\t\"productName\": \"坚果 3 \\\"足迹\\\"背贴 乐高创始人出生\",\n" +
                "\t\t\t\t\t\"subTitle\": \"1891 年 4 月 7 日\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/abb6986430536cd9365352b434f3c568.jpg\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 10,\n" +
                "\t\t\t\"name\": \"品牌周边\",\n" +
                "\t\t\t\"type\": 3,\n" +
                "\t\t\t\"sortOrder\": 4,\n" +
                "\t\t\t\"position\": 0,\n" +
                "\t\t\t\"limitNum\": 7,\n" +
                "\t\t\t\"status\": 1,\n" +
                "\t\t\t\"remark\": null,\n" +
                "\t\t\t\"created\": 1524066632000,\n" +
                "\t\t\t\"updated\": 1524066635000,\n" +
                "\t\t\t\"panelContents\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 40,\n" +
                "\t\t\t\t\t\"panelId\": 10,\n" +
                "\t\t\t\t\t\"type\": 3,\n" +
                "\t\t\t\t\t\"productId\": null,\n" +
                "\t\t\t\t\t\"sortOrder\": 0,\n" +
                "\t\t\t\t\t\"fullUrl\": \"http://xmall.exrick.cn/#/goods?cid=1184\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/z/zhoubianshangpin1220858web.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524067373000,\n" +
                "\t\t\t\t\t\"updated\": 1524194159000,\n" +
                "\t\t\t\t\t\"salePrice\": null,\n" +
                "\t\t\t\t\t\"productName\": null,\n" +
                "\t\t\t\t\t\"subTitle\": null,\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/z/zhoubianshangpin1220858web.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 41,\n" +
                "\t\t\t\t\t\"panelId\": 10,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432845,\n" +
                "\t\t\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/2f9a0f5f3dedf0ed813622003f1b287b.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524067376000,\n" +
                "\t\t\t\t\t\"updated\": 1524155076000,\n" +
                "\t\t\t\t\t\"salePrice\": 199,\n" +
                "\t\t\t\t\t\"productName\": \"Smartisan 帆布鞋\",\n" +
                "\t\t\t\t\t\"subTitle\": \"一双踏实、舒适的帆布鞋\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/2f9a0f5f3dedf0ed813622003f1b287b.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 42,\n" +
                "\t\t\t\t\t\"panelId\": 10,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432836,\n" +
                "\t\t\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/2b05dbca9f5a4d0c1270123f42fb834c.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524067380000,\n" +
                "\t\t\t\t\t\"updated\": 1524155101000,\n" +
                "\t\t\t\t\t\"salePrice\": 149,\n" +
                "\t\t\t\t\t\"productName\": \"Smartisan T恤 伍迪·艾伦出生\",\n" +
                "\t\t\t\t\t\"subTitle\": \"一件内外兼修的舒适T恤\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/2b05dbca9f5a4d0c1270123f42fb834c.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 43,\n" +
                "\t\t\t\t\t\"panelId\": 10,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432846,\n" +
                "\t\t\t\t\t\"sortOrder\": 3,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/804edf579887b3e1fae4e20a379be5b5.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524067384000,\n" +
                "\t\t\t\t\t\"updated\": 1524155117000,\n" +
                "\t\t\t\t\t\"salePrice\": 149,\n" +
                "\t\t\t\t\t\"productName\": \"Smartisan T恤 任天堂发售“红白机”\",\n" +
                "\t\t\t\t\t\"subTitle\": \"100% 美国 SUPIMA 棉、舒适拉绒质地\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/804edf579887b3e1fae4e20a379be5b5.png\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 44,\n" +
                "\t\t\t\t\t\"panelId\": 10,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432848,\n" +
                "\t\t\t\t\t\"sortOrder\": 4,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/a1c53b5f12dd7ef790cadec0eaeaf466.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524067390000,\n" +
                "\t\t\t\t\t\"updated\": 1524192952000,\n" +
                "\t\t\t\t\t\"salePrice\": 199,\n" +
                "\t\t\t\t\t\"productName\": \"Smartisan 牛津纺衬衫\",\n" +
                "\t\t\t\t\t\"subTitle\": \"一件无拘无束的舒适衬衫\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/a1c53b5f12dd7ef790cadec0eaeaf466.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 45,\n" +
                "\t\t\t\t\t\"panelId\": 10,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432847,\n" +
                "\t\t\t\t\t\"sortOrder\": 5,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/daa975651d6d700c0f886718c520ee19.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524067395000,\n" +
                "\t\t\t\t\t\"updated\": 1524192896000,\n" +
                "\t\t\t\t\t\"salePrice\": 249,\n" +
                "\t\t\t\t\t\"productName\": \"Smartisan Polo衫 经典款\",\n" +
                "\t\t\t\t\t\"subTitle\": \"一件表里如一的舒适 POLO 衫\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/daa975651d6d700c0f886718c520ee19.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 46,\n" +
                "\t\t\t\t\t\"panelId\": 10,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432849,\n" +
                "\t\t\t\t\t\"sortOrder\": 6,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/3973d009d182d8023bea6250b9a3959e.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524067400000,\n" +
                "\t\t\t\t\t\"updated\": 1524192903000,\n" +
                "\t\t\t\t\t\"salePrice\": 9.9,\n" +
                "\t\t\t\t\t\"productName\": \"Smartisan 明信片\",\n" +
                "\t\t\t\t\t\"subTitle\": \"优质卡纸、包装精致、色彩饱满\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/3973d009d182d8023bea6250b9a3959e.jpg\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 3,\n" +
                "\t\t\t\"name\": \"品牌精选\",\n" +
                "\t\t\t\"type\": 3,\n" +
                "\t\t\t\"sortOrder\": 5,\n" +
                "\t\t\t\"position\": 0,\n" +
                "\t\t\t\"limitNum\": 7,\n" +
                "\t\t\t\"status\": 1,\n" +
                "\t\t\t\"remark\": \"\",\n" +
                "\t\t\t\"created\": 1524066559000,\n" +
                "\t\t\t\"updated\": 1523962455000,\n" +
                "\t\t\t\"panelContents\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 30,\n" +
                "\t\t\t\t\t\"panelId\": 3,\n" +
                "\t\t\t\t\t\"type\": 2,\n" +
                "\t\t\t\t\t\"productId\": 150642571432850,\n" +
                "\t\t\t\t\t\"sortOrder\": 0,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/a/acillouceng1220856.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1523794518000,\n" +
                "\t\t\t\t\t\"updated\": 1524194283000,\n" +
                "\t\t\t\t\t\"salePrice\": 199,\n" +
                "\t\t\t\t\t\"productName\": \"ACIL E1 颈挂式蓝牙耳机\",\n" +
                "\t\t\t\t\t\"subTitle\": \"无感佩戴，一切变得简单\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/a/acillouceng1220856.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"panelId\": 3,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432851,\n" +
                "\t\t\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/7ac3af5a92ad791c2b38bfe1e38ee334.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1506096182000,\n" +
                "\t\t\t\t\t\"updated\": 1524155020000,\n" +
                "\t\t\t\t\t\"salePrice\": 2699,\n" +
                "\t\t\t\t\t\"productName\": \"优点智能 E1 推拉式智能指纹密码锁\",\n" +
                "\t\t\t\t\t\"subTitle\": \"推拉一下，轻松开关\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/7ac3af5a92ad791c2b38bfe1e38ee334.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 7,\n" +
                "\t\t\t\t\t\"panelId\": 3,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 816448,\n" +
                "\t\t\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/41cb562a47d4831e199ed7e2057f3b61.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1506178691000,\n" +
                "\t\t\t\t\t\"updated\": 1524154469000,\n" +
                "\t\t\t\t\t\"salePrice\": 2799,\n" +
                "\t\t\t\t\t\"productName\": \"极米无屏电视 CC\",\n" +
                "\t\t\t\t\t\"subTitle\": \"720P 高清分辨率、JBL 音响、两万毫安续航力\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/41cb562a47d4831e199ed7e2057f3b61.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 18,\n" +
                "\t\t\t\t\t\"panelId\": 3,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 847276,\n" +
                "\t\t\t\t\t\"sortOrder\": 3,\n" +
                "\t\t\t\t\t\"fullUrl\": null,\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/99c548bfc9848a8c95f4e4f7f2bc1095.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508682172000,\n" +
                "\t\t\t\t\t\"updated\": 1508682172000,\n" +
                "\t\t\t\t\t\"salePrice\": 1499,\n" +
                "\t\t\t\t\t\"productName\": \"FIIL Diva Pro 全场景无线降噪耳机\",\n" +
                "\t\t\t\t\t\"subTitle\": \"智能语音交互、高清无损本地存储播放\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/99c548bfc9848a8c95f4e4f7f2bc1095.png\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 19,\n" +
                "\t\t\t\t\t\"panelId\": 3,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432844,\n" +
                "\t\t\t\t\t\"sortOrder\": 4,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/71432ad30288fb860a4389881069b874.png\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508682215000,\n" +
                "\t\t\t\t\t\"updated\": 1524194738000,\n" +
                "\t\t\t\t\t\"salePrice\": 2999,\n" +
                "\t\t\t\t\t\"productName\": \"畅呼吸智能空气净化器超级除甲\u009Bç\u0089\u0088\",\n" +
                "\t\t\t\t\t\"subTitle\": \"è´\u00ADæ\u0096°ç©ºå\u0087\u0080 èµ ä»·å\u0080¼ 699 å\u0085\u0083æ´»æ\u0080§ç\u0082\u00ADæ»¤è\u008A¯\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/71432ad30288fb860a4389881069b874.png\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 20,\n" +
                "\t\t\t\t\t\"panelId\": 3,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 847276,\n" +
                "\t\t\t\t\t\"sortOrder\": 5,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/804b82e4c05516e822667a23ee311f7c.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508682294000,\n" +
                "\t\t\t\t\t\"updated\": 1524154503000,\n" +
                "\t\t\t\t\t\"salePrice\": 1499,\n" +
                "\t\t\t\t\t\"productName\": \"FIIL Diva Pro å\u0085¨å\u009Cºæ\u0099¯æ\u0097 çº¿é\u0099\u008Då\u0099ªè\u0080³æ\u009Cº\",\n" +
                "\t\t\t\t\t\"subTitle\": \"æ\u0099ºè\u0083½è¯\u00ADé\u009F³äº¤äº\u0092ã\u0080\u0081é«\u0098æ¸\u0085æ\u0097 æ\u008D\u009Fæ\u009C¬å\u009C°å\u00AD\u0098å\u0082¨æ\u0092\u00ADæ\u0094¾\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/804b82e4c05516e822667a23ee311f7c.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 21,\n" +
                "\t\t\t\t\t\"panelId\": 3,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432852,\n" +
                "\t\t\t\t\t\"sortOrder\": 6,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/367d93db1d58f9f3505bc0ec18efaa44.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1508682328000,\n" +
                "\t\t\t\t\t\"updated\": 1524155051000,\n" +
                "\t\t\t\t\t\"salePrice\": 499,\n" +
                "\t\t\t\t\t\"productName\": \"FIIL Driifter è\u0084\u0096æ\u008C\u0082è\u0093\u009Dç\u0089\u0099è\u0080³æ\u009Cº\",\n" +
                "\t\t\t\t\t\"subTitle\": \"å\u0085¨å¤©ä½©æ\u0088´ æ\u008A¬æ\u0089\u008Bå°±å\u0090¬ HEACç¨³è¿\u009Eæ\u008A\u0080æ\u009C¯\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/367d93db1d58f9f3505bc0ec18efaa44.jpg\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 9,\n" +
                "\t\t\t\"name\": \"æ´»å\u008A¨ç\u0089\u0088å\u009D\u00972\",\n" +
                "\t\t\t\"type\": 1,\n" +
                "\t\t\t\"sortOrder\": 7,\n" +
                "\t\t\t\"position\": 0,\n" +
                "\t\t\t\"limitNum\": 4,\n" +
                "\t\t\t\"status\": 1,\n" +
                "\t\t\t\"remark\": \"\",\n" +
                "\t\t\t\"created\": null,\n" +
                "\t\t\t\"updated\": 1524110267000,\n" +
                "\t\t\t\"panelContents\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 65,\n" +
                "\t\t\t\t\t\"panelId\": 9,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150635087972564,\n" +
                "\t\t\t\t\t\"sortOrder\": 1,\n" +
                "\t\t\t\t\t\"fullUrl\": \"\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/f82c9e2774ce0e391a17f1b20c1e0c90.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1554871004000,\n" +
                "\t\t\t\t\t\"updated\": 1554871004000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"æ\u0094¯ä»\u0098æµ\u008Bè¯\u0095å\u0095\u0086å\u0093\u0081 IPhone X å\u0085¨é\u009D¢å±\u008F å\u0085¨é\u009D¢ç»½æ\u0094¾\",\n" +
                "\t\t\t\t\t\"subTitle\": \"æ\u00AD¤ä»\u0085ä¸ºæ\u0094¯ä»\u0098æµ\u008Bè¯\u0095å\u0095\u0086å\u0093\u0081 æ\u008B\u008Dä¸\u008Bä¸\u008Dä¼\u009Aå\u008F\u0091è´§\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/f82c9e2774ce0e391a17f1b20c1e0c90.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 37,\n" +
                "\t\t\t\t\t\"panelId\": 9,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432835,\n" +
                "\t\t\t\t\t\"sortOrder\": 2,\n" +
                "\t\t\t\t\t\"fullUrl\": \"https://www.smartisan.com/os/#/4-x\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/5ea6f0905535d7b11258e9a0f9b1abeb.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524066711000,\n" +
                "\t\t\t\t\t\"updated\": 1524196999000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"æ\u008D\u0090èµ å\u0095\u0086å\u0093\u0081\",\n" +
                "\t\t\t\t\t\"subTitle\": \"æ\u0082¨ç\u009A\u0084æ\u008D\u0090èµ å°\u0086ç\u0094¨äº\u008Eæ\u009C¬ç«\u0099ç»´æ\u008A¤ ç»\u0099æ\u0082¨å¸¦æ\u009D¥æ\u009B´å¥½ç\u009A\u0084ä½\u0093éª\u008C\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/5ea6f0905535d7b11258e9a0f9b1abeb.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 38,\n" +
                "\t\t\t\t\t\"panelId\": 9,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150635087972564,\n" +
                "\t\t\t\t\t\"sortOrder\": 3,\n" +
                "\t\t\t\t\t\"fullUrl\": \"https://www.smartisan.com/pr/#/video/changhuxi-jinghuaqi\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/c245ada282824a4a15e68bec80502ad4.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524066718000,\n" +
                "\t\t\t\t\t\"updated\": 1524197011000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"æ\u0094¯ä»\u0098æµ\u008Bè¯\u0095å\u0095\u0086å\u0093\u0081 IPhone X å\u0085¨é\u009D¢å±\u008F å\u0085¨é\u009D¢ç»½æ\u0094¾\",\n" +
                "\t\t\t\t\t\"subTitle\": \"æ\u00AD¤ä»\u0085ä¸ºæ\u0094¯ä»\u0098æµ\u008Bè¯\u0095å\u0095\u0086å\u0093\u0081 æ\u008B\u008Dä¸\u008Bä¸\u008Dä¼\u009Aå\u008F\u0091è´§\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/c245ada282824a4a15e68bec80502ad4.jpg\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 39,\n" +
                "\t\t\t\t\t\"panelId\": 9,\n" +
                "\t\t\t\t\t\"type\": 0,\n" +
                "\t\t\t\t\t\"productId\": 150642571432835,\n" +
                "\t\t\t\t\t\"sortOrder\": 4,\n" +
                "\t\t\t\t\t\"fullUrl\": \"https://www.smartisan.com/pr/#/video/onestep-introduction\",\n" +
                "\t\t\t\t\t\"picUrl\": \"https://resource.smartisan.com/resource/m/minibanner_03.jpg\",\n" +
                "\t\t\t\t\t\"picUrl2\": null,\n" +
                "\t\t\t\t\t\"picUrl3\": null,\n" +
                "\t\t\t\t\t\"created\": 1524066722000,\n" +
                "\t\t\t\t\t\"updated\": 1524197021000,\n" +
                "\t\t\t\t\t\"salePrice\": 1,\n" +
                "\t\t\t\t\t\"productName\": \"æ\u008D\u0090èµ å\u0095\u0086å\u0093\u0081\",\n" +
                "\t\t\t\t\t\"subTitle\": \"æ\u0082¨ç\u009A\u0084æ\u008D\u0090èµ å°\u0086ç\u0094¨äº\u008Eæ\u009C¬ç«\u0099ç»´æ\u008A¤ ç»\u0099æ\u0082¨å¸¦æ\u009D¥æ\u009B´å¥½ç\u009A\u0084ä½\u0093éª\u008C\",\n" +
                "\t\t\t\t\t\"productImageBig\": \"https://resource.smartisan.com/resource/m/minibanner_03.jpg\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]";

//        TbPanelContent content = new TbPanelContent();
//        content.setId(1);
//        content.setProductName("xxx");
//        content.setPicUrl("https://ooo.0o0.ooo/2019/09/30/lLv8xp3IWqa7Oz6.png");
//        content.setPicUrl2("https://ooo.0o0.ooo/2018/11/04/5bdeba6753403.png");
//        content.setPicUrl3("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2534199263,3708083788&fm=11&gp=0.jpg");

        return JSONObject.parseArray(data, TbPanel.class);
//
//        TbPanel build = TbPanel.builder().id(1)
//                .limitNum(2)
//                .name("xx")
//                .created(new Date())
//                .status(1)
//                .type(0)
//                .remark("yy")
//                .sortOrder(1)
//                .position(1)
//                .updated(new Date())
//                .panelContents(Collections.singletonList(content)).build();
//        return Collections.singletonList(build);

//        List<TbPanel> list=new ArrayList<>();
//
//        //查询缓存
//        try{
//            //有缓存则读取
//            String json=jedisClient.get(PRODUCT_HOME);
//            if(json!=null){
//                list = new Gson().fromJson(json, new TypeToken<List<TbPanel>>(){}.getType());
//                log.info("读取了首页缓存");
//                return list;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        //没有缓存
//        TbPanelExample example=new TbPanelExample();
//        TbPanelExample.Criteria criteria=example.createCriteria();
//        //条件查询
//        criteria.andPositionEqualTo(0);
//        criteria.andStatusEqualTo(1);
//        example.setOrderByClause("sort_order");
//        list=tbPanelMapper.selectByExample(example);
//        for(TbPanel tbPanel:list){
//            TbPanelContentExample exampleContent=new TbPanelContentExample();
//            exampleContent.setOrderByClause("sort_order");
//            TbPanelContentExample.Criteria criteriaContent=exampleContent.createCriteria();
//            //条件查询
//            criteriaContent.andPanelIdEqualTo(tbPanel.getId());
//            List<TbPanelContent> contentList=tbPanelContentMapper.selectByExample(exampleContent);
//            for(TbPanelContent content:contentList){
//                if(content.getProductId()!=null){
//                    TbItem tbItem=tbItemMapper.selectByPrimaryKey(content.getProductId());
//                    content.setProductName(tbItem.getTitle());
//                    content.setSalePrice(tbItem.getPrice());
//                    content.setSubTitle(tbItem.getSellPoint());
//                }
//            }
//
//            tbPanel.setPanelContents(contentList);
//        }
//
//        //把结果添加至缓存
//        try{
//            jedisClient.set(PRODUCT_HOME, new Gson().toJson(list));
//            log.info("添加了首页缓存");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return list;
    }
/*
    @Override
    public List<TbPanel> getRecommendGoods() {


        List<TbPanel> list = new ArrayList<>();
        //查询缓存
        try{
            //有缓存则读取
            String json=jedisClient.get(RECOMEED_PANEL);
            if(json!=null){
                list = new Gson().fromJson(json, new TypeToken<List<TbPanel>>(){}.getType());
                log.info("读取了推荐板块缓存");
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        list = getTbPanelAndContentsById(RECOMEED_PANEL_ID);
        //把结果添加至缓存
        try{
            jedisClient.set(RECOMEED_PANEL, new Gson().toJson(list));
            log.info("添加了推荐板块缓存");
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TbPanel> getThankGoods() {

        List<TbPanel> list = new ArrayList<>();
        //查询缓存
        try{
            //有缓存则读取
            String json=jedisClient.get(THANK_PANEL);
            if(json!=null){
                list = new Gson().fromJson(json, new TypeToken<List<TbPanel>>(){}.getType());
                log.info("读取了捐赠板块缓存");
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        list = getTbPanelAndContentsById(THANK_PANEL_ID);
        //把结果添加至缓存
        try{
            jedisClient.set(THANK_PANEL, new Gson().toJson(list));
            log.info("添加了捐赠板块缓存");
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    List<TbPanel> getTbPanelAndContentsById(Integer panelId){

        List<TbPanel> list=new ArrayList<>();
        TbPanelExample example=new TbPanelExample();
        TbPanelExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andIdEqualTo(panelId);
        criteria.andStatusEqualTo(1);
        list=tbPanelMapper.selectByExample(example);
        for(TbPanel tbPanel:list){
            TbPanelContentExample exampleContent=new TbPanelContentExample();
            exampleContent.setOrderByClause("sort_order");
            TbPanelContentExample.Criteria criteriaContent=exampleContent.createCriteria();
            //条件查询
            criteriaContent.andPanelIdEqualTo(tbPanel.getId());
            List<TbPanelContent> contentList=tbPanelContentMapper.selectByExample(exampleContent);
            for(TbPanelContent content:contentList){
                if(content.getProductId()!=null){
                    TbItem tbItem=tbItemMapper.selectByPrimaryKey(content.getProductId());
                    content.setProductName(tbItem.getTitle());
                    content.setSalePrice(tbItem.getPrice());
                    content.setSubTitle(tbItem.getSellPoint());
                }
            }

            tbPanel.setPanelContents(contentList);
        }
        return list;
    }

    @Override
    public ProductDet getProductDet(Long id) {

        //查询缓存
        try{
            //有缓存则读取
            String json=jedisClient.get(PRODUCT_ITEM+":"+id);
            if(json!=null){
                ProductDet productDet= new Gson().fromJson(json,ProductDet.class);
                log.info("读取了商品"+id+"详情缓存");
                //重置商品缓存时间
                jedisClient.expire(PRODUCT_ITEM+":"+id,ITEM_EXPIRE);
                return productDet;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbItem tbItem=tbItemMapper.selectByPrimaryKey(id);
        ProductDet productDet=new ProductDet();
        productDet.setProductId(id);
        productDet.setProductName(tbItem.getTitle());
        productDet.setSubTitle(tbItem.getSellPoint());
        if(tbItem.getLimitNum()!=null&&!tbItem.getLimitNum().toString().isEmpty()){
            productDet.setLimitNum(Long.valueOf(tbItem.getLimitNum()));
        }else{
            productDet.setLimitNum(Long.valueOf(tbItem.getNum()));
        }
        productDet.setSalePrice(tbItem.getPrice());

        TbItemDesc tbItemDesc=tbItemDescMapper.selectByPrimaryKey(id);
        productDet.setDetail(tbItemDesc.getItemDesc());

        if(tbItem.getImage()!=null&&!tbItem.getImage().isEmpty()){
            String images[]=tbItem.getImage().split(",");
            productDet.setProductImageBig(images[0]);
            List list=new ArrayList();
            for(int i=0;i<images.length;i++){
                list.add(images[i]);
            }
            productDet.setProductImageSmall(list);
        }
        //无缓存 把结果添加至缓存
        try{
            jedisClient.set(PRODUCT_ITEM+":"+id,new Gson().toJson(productDet));
            //设置过期时间
            jedisClient.expire(PRODUCT_ITEM+":"+id,ITEM_EXPIRE);
            log.info("添加了商品"+id+"详情缓存");
        }catch (Exception e){
            e.printStackTrace();
        }
        return productDet;
    }*/

    @Override
    public ResultPageView<ProductInfo> getAllProduct(int page, int size,
                                String sort, Long cid,
                                Integer priceGt, Integer priceLte) {
         PageHelper.startPage(page, size);
        ProductQueryCondition build = ProductQueryCondition.builder().cid(cid)
                .sort(sort).priceGt(priceGt).priceLte(priceLte).build();
        List<ProductInfo>  productInfos =  productInfoMapper.selectByCondition(build);
        PageInfo<ProductInfo> productInfoPageInfo = new PageInfo<>(productInfos);

        return new ResultPageView<>(productInfoPageInfo);

    }

 /*   @Override
    public String getIndexRedis() {

        try{
            String json=jedisClient.get(PRODUCT_HOME);
            return json;
        }catch (Exception e){
            log.error(e.toString());
        }
        return "";
    }

    @Override
    public int updateIndexRedis() {

        deleteHomeRedis();
        return 1;
    }

    @Override
    public String getRecommendRedis() {

        try{
            String json=jedisClient.get(RECOMEED_PANEL);
            return json;
        }catch (Exception e){
            log.error(e.toString());
        }
        return "";
    }

    @Override
    public int updateRecommendRedis() {

        try {
            jedisClient.del(RECOMEED_PANEL);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public String getThankRedis() {

        try{
            String json=jedisClient.get(THANK_PANEL);
            return json;
        }catch (Exception e){
            log.error(e.toString());
        }
        return "";
    }

    @Override
    public int updateThankRedis() {

        try {
            jedisClient.del(THANK_PANEL);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    public void updateNavListRedis() {

        try {
            jedisClient.del(HEADER_PANEL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<TbPanelContent> getNavList() {

        List<TbPanelContent> list = new ArrayList<>();
        //查询缓存
        try{
            //有缓存则读取
            String json=jedisClient.get(HEADER_PANEL);
            if(json!=null){
                list = new Gson().fromJson(json, new TypeToken<List<TbPanelContent>>(){}.getType());
                log.info("读取了导航栏缓存");
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbPanelContentExample exampleContent=new TbPanelContentExample();
        exampleContent.setOrderByClause("sort_order");
        TbPanelContentExample.Criteria criteriaContent=exampleContent.createCriteria();
        //条件查询
        criteriaContent.andPanelIdEqualTo(HEADER_PANEL_ID);
        list=tbPanelContentMapper.selectByExample(exampleContent);

        //把结果添加至缓存
        try{
            jedisClient.set(HEADER_PANEL, new Gson().toJson(list));
            log.info("添加了导航栏缓存");
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    *//**
     * 同步首页缓存
     *//*
    public void deleteHomeRedis(){
        try {
            jedisClient.del(PRODUCT_HOME);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}

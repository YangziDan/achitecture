package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Service
public class BusinessService extends LogicService<BusinessDao, Business,Long> {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ItemService itemService;
    BusinessService(@Autowired BusinessDao businessDao){super(businessDao);}
    @Transactional
    public Boolean addShop(Long bus_id,Shop shop){
        Business business=getDAO().getReferenceById(bus_id);
        if(business==null){
            return false;
        }
        return business.addShop(shop);
    }
    @Transactional
    public Boolean addItem(Long bus_id, Item item){
        Business business =GET(bus_id);
        if(business==null){
            return false;
        }
        return business.addItem(item);
    }
    //listItem为上架商品
    @Transactional
    public Boolean listItem(Long bus_id,Long shop_id, Long item_id,Double price){
        Business business=GET(bus_id);
        Item item=itemService.GET(item_id);
        Shop shop=shopService.GET(shop_id);
        if(business==null||shop==null||item==null){
            return false;
        }
        return business.listItem(shop,item,price);
    }
    @Transactional
    public Boolean confirmOrder(Long bus_id,Long shop_id,Long order_id){
        Business business=GET(bus_id);
        Shop shop=shopService.GET(shop_id);
        if(shop.getBusiness().equals(business)){
            return shopService.confirmOrder(shop_id,order_id);
        }
        return false;
    }
    @Transactional
    public Boolean cancelOrder(Long bus_id,Long shop_id,Long order_id){
        Business business=GET(bus_id);
        Shop shop=shopService.GET(shop_id);
        if(shop.getBusiness().equals(business)){
            return shopService.cancelOrder(shop_id,order_id);
        }
        return false;
    }
    @Transactional
    public Set<Shop> getAllMyShop(Long bus_id){
        return GET(bus_id).getShopList();
    }
    public Set<Order> getOrderFromShop(Long bus_id, Long shop_id){
        Shop shop=shopService.GET(shop_id);
        if (getAllMyShop(bus_id).contains(shop)){
            return shop.getOrders();
        }
        return null;
    }
    public Set<Item> findAllItem(Long bus_id){
        return GET(bus_id).getItemList();
    }

}

package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShopService extends LogicService<ShopDao, Shop,Long> {
    public ShopService(@Autowired ShopDao lr) {
        super(lr);
    }
    @Autowired
    public OrderService orderService;
    @Autowired
    public ItemService itemService;
    @Transactional
    public Boolean confirmOrder(Long shop_id,Long order_id){
        Shop shop=GET(shop_id);
        Order order=orderService.GET(order_id);
        if (order.getState().equals("已支付")){
            order.setState("已确认");
            orderService.PUT(order);
            return true;
        }
        return false;
    }
    @Transactional
    public Boolean cancelOrder(Long shop_id,Long order_id){
        Shop shop=GET(shop_id);
        Order order=orderService.GET(order_id);
        if (order.getState().equals("已支付")){
            order.setState("已退款");
            orderService.PUT(order);
            return true;
        }
        return false;
    }

    @Transactional
    public Set<Item> findALlItems(Long shop_id){
        Set<ShopItem> shopItems=GET(shop_id).getShopItems();
        Set<Item> items=new HashSet<>();
        for (ShopItem shopItem : shopItems) {
            items.add(shopItem.getItem());
        }
        return items;

    }
}

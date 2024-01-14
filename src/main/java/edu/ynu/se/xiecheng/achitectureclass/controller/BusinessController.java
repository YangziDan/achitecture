package edu.ynu.se.xiecheng.achitectureclass.controller;
import edu.ynu.se.xiecheng.achitectureclass.controller.Httpentity.ListItem;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@CrossOrigin
@RestController
public class BusinessController  {
    @Autowired
    BusinessService businessService;
    @GetMapping("/business/order")
    public Set<Order> findOrderFromShop(@RequestParam Long bus_id,@RequestParam Long shop_id){
        return businessService.getOrderFromShop(bus_id,shop_id);
    }
    @GetMapping("/business/shop")
    public Set<Shop> findAllShop(@RequestParam Long bus_id){
        return businessService.getAllMyShop(bus_id);
    }
    @GetMapping("/business/item")
    public Set<Item> findAllItem(@RequestParam Long bus_id){
        return businessService.findAllItem(bus_id);
    }
    @GetMapping("/business/newItem")
    public Boolean addItem(@RequestParam Long bus_id,@RequestParam Double price,@RequestParam String name){
        Item item=new Item();
        item.setName(name);
        item.setGuidePrice(price);
        item.setBusiness(businessService.GET(bus_id));
        return businessService.addItem(bus_id,item);
    }
    @PostMapping("/business/listItem")
    public Boolean listItem(@RequestBody ListItem listItem){
        return businessService.listItem(listItem.getBus_id(),listItem.getShop_id(),listItem.getItem_id(),listItem.getPrice());
    }
    @PostMapping("/business/confirm")
    public Boolean confirm(@RequestParam Long bus_id,@RequestParam Long shop_id,@RequestParam Long order_id){
        return businessService.confirmOrder(bus_id,shop_id,order_id);
    }
    @PostMapping("/business/refund")
    public Boolean refund(@RequestParam Long bus_id,@RequestParam Long shop_id,@RequestParam Long order_id){
        return businessService.cancelOrder(bus_id,shop_id,order_id);
    }

}

package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.controller.Httpentity.PickOrder;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.UserDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.*;
import edu.ynu.se.xiecheng.achitectureclass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CustomerController  {
    @Autowired
    CustomerService customerService;
    @Autowired
    BusinessService businessService;
    @Autowired
    ShopService shopService;
    @Autowired
    ItemService itemService;
    @GetMapping("/allShop")
    public List<Shop> findAllShop(){
        return shopService.getAll();
    }
    @GetMapping("/allFood")
    public Set<Item> findAllFoodByShop(@RequestParam("shopId") Long shopId){
        return shopService.findALlItems(shopId);
    }
    //pick为顾客挑选商品的方法
    @PostMapping("/pick")
    public Order pick(@RequestBody PickOrder pickOrder){
        return customerService.pick(pickOrder.getCus_id(),pickOrder.getShop_id(),pickOrder.getItem_id(), pickOrder.getQuantity());
    }
    @GetMapping("/allOrder")
    public Set<Order> findAllOrder(@RequestParam("cus_id") Long cus_id){
        return customerService.getAllOrder(cus_id);
    }
}

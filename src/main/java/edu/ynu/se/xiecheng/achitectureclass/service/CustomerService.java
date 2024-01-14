package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.*;
import edu.ynu.se.xiecheng.achitectureclass.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService extends LogicService<CustomerDao, Customer,Long> {
    CustomerService(@Autowired CustomerDao dao,
                    LineItemDao lineItemDao){
        super(dao);
        this.lineItemDao = lineItemDao;
    }
    @Autowired
    ShopService shopService;
    @Autowired
    OrderService orderService;
    @Autowired
    LineItemService lineItemService;
    private final LineItemDao lineItemDao;

    @Transactional
    public Order pick(Long cus_id, Long shop_id,Long item_id,int quantity){
        Set<LineItem> lineItems=new HashSet<>();
        Customer customer=GET(cus_id);
        if (customer==null){
            return null;
        }
        Shop shop=shopService.GET(shop_id);
        if (shop==null)
            return null;
        Order order=new Order();
        order.setShop(shop);
        order.setCustomer(customer);
        for (ShopItem shopItem : shop.getShopItems()) {
            Item item=shopItem.getItem();
            if(item.getId().equals(item_id)){
                LineItem lineItem=new LineItem();
                lineItem.setQuantity(quantity);
                lineItem.setShopItem(shopItem);
                lineItems.add(lineItem);
            }
        }
        for (LineItem lineItem : lineItems) {
            lineItem.setOrder(order);
        }
        order.setLineItems(lineItems);
        Set<Order> orders=customer.getOrders();
        for (Order order1 : orders) {
            if(order1.isSameOrder(order))
            {
                lineItems.addAll(order1.getLineItems());
                order.setId(order1.getId());
                orderService.PUT(order);
                return order;
            }
        }
        orderService.POST(order);
        return order;
    }
    @Transactional
    public Boolean cancel(Long cus_id,Long order_id){
        Customer customer=GET(cus_id);
        Order order= orderService.GET(order_id);
        if (customer.getOrders().contains(order)&&order.getState().equals("未支付")){
            orderService.DELETE(order_id);
            return true;
        }
        return false;
    }
    @Transactional
    public Boolean pay(Long cus_id,Long order_id){
        Customer customer=GET(cus_id);
        Order order= orderService.GET(order_id);
        if (customer.getOrders().contains(order)&&order.getState().equals("未支付")){
            order.setState("已支付");
            orderService.PUT(order);
            return true;
        }
        return false;
    }
    @Transactional
    public Set<Order> getAllOrder(Long cus_id){
        return GET(cus_id).getOrders();
    }
}

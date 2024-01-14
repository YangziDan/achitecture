package edu.ynu.se.xiecheng.achitectureclass;

import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.*;
import edu.ynu.se.xiecheng.achitectureclass.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class CustomerTest {
    @Autowired
    CustomerDao dao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    CustomerService customerService;
    @Autowired
    ShopItemDao shopItemDao;
    @Autowired
    OrderDao orderDao;
    @Test
    void saveCus() {
       Customer customer=new Customer();
       customer.setName("顾客小林");
       customer.setPassword("123");
       dao.save(customer);
    }
    @Test
    void  saveOrder(){
        Order order=new Order();
        Set<LineItem> lineItems=new HashSet<>();
        LineItem lineItem=new LineItem();
        lineItem.setShopItem(shopItemDao.getReferenceById(1L));
        lineItem.setQuantity(2);
        lineItems.add(lineItem);
        order.setShop(shopDao.getReferenceById(1L));
        order.setLineItems(lineItems);
        order.setCustomer(dao.getReferenceById(2L));
        orderDao.save(order);
    }
    @Test
    void pick() {
        Shop shop=shopDao.findById(1L).get();
        Set<ShopItem> shopItems=shop.getShopItems();
        Set<LineItem> lineItems=new HashSet<>();
        LineItem lineItem=new LineItem();
        lineItem.setShopItem(shopItemDao.getReferenceById(1L));
        lineItem.setQuantity(2);
        lineItems.add(lineItem);
        lineItems.add(lineItem);
        customerService.pick(2L,shop.getId(),1L,5);
    }
    @Test
    void  cancel(){
        customerService.cancel(2L,5L);
    }
    @Test
    void pay(){
        customerService.pay(2L,8L);
    }
    @Test
    void password(){
        Customer customer=new Customer();
        customer.setPassword("1");
        System.out.println(customer.getPassword());
    }

}

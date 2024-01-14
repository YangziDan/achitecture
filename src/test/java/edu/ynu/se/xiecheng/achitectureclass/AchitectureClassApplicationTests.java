package edu.ynu.se.xiecheng.achitectureclass;

import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AchitectureClassApplicationTests {
    @Autowired
    BusinessDao dao;
    @Test
    void saveBus() {
        Business business=new Business();
        business.setBusinessName("商家小林");
        business.setName("1");
        business.setPassword("1");
        dao.save(business);
    }
    @Autowired
    BusinessService businessService;
    @Test
    void addShop() {
        Shop shop=new Shop();
        shop.setName("3店");
        businessService.addShop(1L,shop);
    }
    @Test
    void addItem(){
        Item item=new Item();
        item.setName("中水杯");
        item.setGuidePrice(12.8);
        businessService.addItem(1L,item);
    }
    @Test
    void listItem(){
        System.out.println(businessService.listItem(1L,1L,3L,7.0));
    }
}

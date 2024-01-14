package edu.ynu.se.xiecheng.achitectureclass.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@DiscriminatorValue("1")
@Where(clause = "is_deleted = 0")
public class Business extends User {
    @Column
    private String businessName;
    @OneToMany(mappedBy = "business",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Shop> shopList;
    @OneToMany(mappedBy = "business",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Item> itemList;

    public Boolean addShop(Shop shop) {
        shop.setBusiness(this);
        return shopList.add(shop);
    }
    public Boolean addItem(Item item) {
        item.setBusiness(this);
        return itemList.add(item);
    }
    public Boolean listItem(Shop shop,Item item,Double price){
        if (!isMyShop(shop))
            return false;
        if(!shop.addItem(item,price)){
            return false;
        }
        return true;
    }
    public Set<Order>  viewOrdersFromShop(Shop shop){
        if (!isMyShop(shop))
            return null;
        return shop.getOrders();
    }
    private Boolean isMyShop(Shop shop){
        if (!shopList.contains(shop))
            return  false;
        return true;
    }
}

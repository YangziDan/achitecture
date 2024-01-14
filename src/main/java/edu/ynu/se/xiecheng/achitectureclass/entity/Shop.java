package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.BaseEntity;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class Shop extends LogicEntity {
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Business business;
    @OneToMany(mappedBy = "shop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ShopItem> shopItems;
    @OneToMany(mappedBy = "shop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders;
    public Boolean addItem(Item item,Double price){
        ShopItem shopItem=new ShopItem();
        shopItem.setShop(this);
        shopItem.setItem(item);
        shopItem.setPrice(price);
        item.getShopItems().add(shopItem);
        return shopItems.add(shopItem);
    }
}

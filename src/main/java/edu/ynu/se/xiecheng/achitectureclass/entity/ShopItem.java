package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
//商品
public class ShopItem extends LogicEntity {
    @Column
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    Shop shop;
    @ManyToOne(fetch = FetchType.LAZY)
    Item item;
    @OneToMany(mappedBy = "shopItem",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<LineItem> lineItems;
}

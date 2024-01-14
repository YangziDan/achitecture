package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.util.DigestUtils;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance
@Where(clause = "is_deleted = 0")
@DiscriminatorColumn(name = "type")
public abstract class User extends LogicEntity {
    @Column
    protected String name;
    @JsonIgnore
    @Column
    protected String password;
    @Column
    protected String account;

    public void setPassword(String psd){
        this.password = DigestUtils.md5DigestAsHex(psd.getBytes());

    }

    @Column(updatable = false, insertable = false)
    protected Integer type;
}

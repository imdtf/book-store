package com.github.imdtf.bookstore.domain.warehouse;

import com.github.imdtf.bookstore.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * εεεΊε­
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:31
 * 4
 */
@Entity
@Getter
@Setter
public class Stockpile extends BaseEntity {

    private Integer amount;

    private Integer frozen;

    @Transient
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void frozen(Integer number) {
        this.amount -= number;
        this.frozen += number;
    }

    public void thawed(Integer number) {
        frozen(-1 * number);
    }

    public void decrease(Integer number) {
        this.frozen -= number;
    }

    public void increase(Integer number) {
        this.amount += number;
    }
}

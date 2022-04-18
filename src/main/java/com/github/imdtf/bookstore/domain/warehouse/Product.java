package com.github.imdtf.bookstore.domain.warehouse;

import com.github.imdtf.bookstore.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/18 22:37
 * 4
 */
@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @NotEmpty
    private String title;

    @NotNull
    @Min(value = 0)
    private Double price;

    @Min(value = 0)
    @Max(value = 10)
    private Float rate;

    private String description;

    private String cover;

    private String detail;
}

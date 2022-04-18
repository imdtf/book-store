package com.github.imdtf.bookstore.domain.warehouse;

import com.github.imdtf.bookstore.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/18 22:23
 * 4
 */
@Entity
@Getter
@Setter
public class Advertisement extends BaseEntity {

    @NotEmpty
    private String image;

    @NotNull
    private Integer productId;
}

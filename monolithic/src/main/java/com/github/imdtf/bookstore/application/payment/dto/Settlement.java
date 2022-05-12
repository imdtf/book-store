package com.github.imdtf.bookstore.application.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.imdtf.bookstore.domain.warehouse.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Map;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:42
 * 4
 */
@Getter
@Setter
public class Settlement {

    @Size(min = 1)
    private Collection<Item> items;

    @NotNull
    private Purchase purchase;

    private transient Map<Integer, Product> productMap;

    @Getter
    @Setter
    public static class Item {

        @NotNull
        @Min(1)
        private Integer amount;

        @NotNull
        @JsonProperty("id")
        private Integer productId;
    }

    @Getter
    @Setter
    public static class Purchase {

        private Boolean delivery = true;

        @NotEmpty
        private String pay;

        @NotEmpty
        private String name;

        @NotEmpty
        private String telephone;

        @NotEmpty
        private String location;
    }
}

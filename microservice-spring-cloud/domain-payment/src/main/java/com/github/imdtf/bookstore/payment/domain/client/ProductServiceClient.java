package com.github.imdtf.bookstore.payment.domain.client;

import com.github.imdtf.bookstore.domain.warehouse.DeliveredStatus;
import com.github.imdtf.bookstore.domain.warehouse.Product;
import com.github.imdtf.bookstore.domain.warehouse.Stockpile;
import com.github.imdtf.bookstore.dto.Settlement;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/5/14 22:32
 * 4
 */
@FeignClient(name = "warehouse")
public interface ProductServiceClient {

    /**
     * 填充订单商品信息
     *
     * @param bill 订单
     */
    default void replenishProductInformation(Settlement bill) {
        bill.setProductMap(Stream.of(getProducts()).collect(Collectors.toMap(Product::getId, Function.identity())));
    }

    // TODO spring annotation
    /**
     * 根据 id 获取产品详情
     *
     * @param id 主键
     * @return 产品
     */
    @GET
    @Path("/restful/products/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Product getProduct(@PathParam("id") Integer id);

    /**
     * 获取产品列表
     *
     * @return 产品列表
     */
    @GET
    @Path("/restful/products")
    @Consumes(MediaType.APPLICATION_JSON)
    Product[] getProducts();

    /**
     * 扣减库存
     *
     * @param productId 产品 id
     * @param amount    数量
     */
    default void decrease(Integer productId, Integer amount) {
        setDeliveredStatus(productId, DeliveredStatus.DECREASE, amount);
    }

    /**
     * 增加库存
     *
     * @param productId 产品 id
     * @param amount    数量
     */
    default void increase(Integer productId, Integer amount) {
        setDeliveredStatus(productId, DeliveredStatus.INCREASE, amount);
    }

    /**
     * 冻结库存
     *
     * @param productId 产品 id
     * @param amount    数量
     */
    default void frozen(Integer productId, Integer amount) {
        setDeliveredStatus(productId, DeliveredStatus.FROZEN, amount);
    }

    /**
     * 恢复冻结库存
     *
     * @param productId 产品 id
     * @param amount    数量
     */
    default void thawed(Integer productId, Integer amount) {
        setDeliveredStatus(productId, DeliveredStatus.THAWED, amount);
    }

    /**
     * 设置库存
     *
     * @param productId 产品 id
     * @param status    状态
     * @param amount    数量
     */
    @PATCH
    @Path("restful/products/stockpile/delivered/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    void setDeliveredStatus(@PathParam("productId") Integer productId, @QueryParam("status") DeliveredStatus status, @QueryParam("amount") Integer amount);

    /**
     * 查询库存
     *
     * @param productId 产品 id
     * @return 库存结果
     */
    @GET
    @Path("/restful/products/stockpile/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Stockpile queryStockpile(@PathParam("productId") Integer productId);
}

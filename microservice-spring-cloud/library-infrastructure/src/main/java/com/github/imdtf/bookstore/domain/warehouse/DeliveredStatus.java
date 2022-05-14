package com.github.imdtf.bookstore.domain.warehouse;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/5/14 22:39
 * 4
 */
public enum DeliveredStatus {

    /**
     * 出库扣减库存
     */
    DECREASE,

    /**
     * 入库增加库存
     */
    INCREASE,

    /**
     * 待出库冻结库存
     */
    FROZEN,

    /**
     * 取消出库解冻库存
     */
    THAWED
}

package com.ya.boottest.manage.order.service;

/**
 * <p>
 *  订单服务
 * </p>
 *
 * @author Ya Shi
 * @since 2023/8/12 16:55
 */
public interface OrderService {

    /**
     * 将订单导出为Excel
     * @param orderNum 订单编号
     */
    Object export(String orderNum);
}

package com.atguigu.ggkt.order.service;

import com.atguigu.ggkt.model.order.OrderInfo;

import com.atguigu.ggkt.vo.order.OrderFormVo;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-09
 */
public interface OrderInfoService extends IService<OrderInfo> {
    //订单列表
    HashMap<String, Object> selectOrderInfoPage(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);
    //生成点播课程订单
    Long submitOrder(OrderFormVo orderFormVo);
}

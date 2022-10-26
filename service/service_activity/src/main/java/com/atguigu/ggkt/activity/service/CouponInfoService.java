package com.atguigu.ggkt.activity.service;


import com.atguigu.ggkt.model.activity.CouponInfo;
import com.atguigu.ggkt.model.activity.CouponUse;
import com.atguigu.ggkt.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-10
 */
public interface CouponInfoService extends IService<CouponInfo> {
    //获取已经使用的优惠券
    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);

    void updateCouponInfoUseStatus(Long couponUseId, Long orderId);
}

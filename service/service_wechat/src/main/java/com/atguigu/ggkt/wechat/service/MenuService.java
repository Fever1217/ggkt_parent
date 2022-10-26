package com.atguigu.ggkt.wechat.service;

import com.atguigu.ggkt.model.wechat.Menu;

import com.atguigu.ggkt.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-12
 */
public interface MenuService extends IService<Menu> {
    //获取所有菜单 按照一级和二级封装
    List<MenuVo> findMenuInfo();
    //获取所有一级菜单
    List<Menu> findMenuOneInfo();

    void syncMenu();

    void removeMenu();
}

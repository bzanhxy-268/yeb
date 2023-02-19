package com.example.yebserver.service;

import com.example.yebserver.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenuByAdminId();

    List<Menu> getMenusWithRole();

    List<Menu> MenusWithChildren();
}

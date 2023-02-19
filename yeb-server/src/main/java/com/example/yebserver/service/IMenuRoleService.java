package com.example.yebserver.service;

import com.example.yebserver.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yebserver.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface IMenuRoleService extends IService<MenuRole> {

    RespBean updateRoleMenu(Integer rid, Integer[] mids);
}

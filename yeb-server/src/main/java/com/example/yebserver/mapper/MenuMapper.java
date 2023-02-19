package com.example.yebserver.mapper;

import com.example.yebserver.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByAdminId(Integer id);

    List<Menu> getMenusWithRole();

    List<Menu> MenusWithChildren();
}

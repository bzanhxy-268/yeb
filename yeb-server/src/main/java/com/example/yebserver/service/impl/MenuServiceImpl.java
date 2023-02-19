package com.example.yebserver.service.impl;

import com.example.yebserver.config.AdminUtils;
import com.example.yebserver.pojo.Menu;
import com.example.yebserver.mapper.MenuMapper;
import com.example.yebserver.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<Menu> getMenuByAdminId() {
        Integer id = AdminUtils.getAdmin().getId();
        ValueOperations<String, Object> valueOprations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) valueOprations.get("menu_" + id);
        if (CollectionUtils.isEmpty(menus)){
            menus=menuMapper.getMenuByAdminId(id);
            valueOprations.set("menu_"+id,menus);
        }
        return menus;
    }

    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    @Override
    public List<Menu> MenusWithChildren() {
        return menuMapper.MenusWithChildren();
    }
}

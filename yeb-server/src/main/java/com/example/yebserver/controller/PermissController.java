package com.example.yebserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.yebserver.pojo.Menu;
import com.example.yebserver.pojo.MenuRole;
import com.example.yebserver.pojo.RespBean;
import com.example.yebserver.pojo.Role;
import com.example.yebserver.service.IMenuRoleService;
import com.example.yebserver.service.IMenuService;
import com.example.yebserver.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value ="查询所有角色" )
    @GetMapping("/")
    public List<Role> Roles(){
        return roleService.list();
    }

    @ApiOperation(value ="添加角色" )
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)){
            return RespBean.success("添加成功");
        }else
            return RespBean.error("添加失败");
    }



    @ApiOperation(value ="删除角色" )
    @DeleteMapping("/role/{rid}")
    public RespBean deleteJoblevel(@PathVariable Integer rid){
        if (roleService.removeById(rid)){
            return RespBean.success("删除成功");
        }else
            return RespBean.error("删除失败");
    }

    @ApiOperation(value = "查询所有菜单列表")
    @GetMapping("/menus")
    public List<Menu> MenusWithChildren(){
        return menuService.MenusWithChildren();
    }


    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        List<Integer> rids = menuRoleService.list(new QueryWrapper<MenuRole>()
                .eq("rid", rid)).stream()
                .map(MenuRole::getMid).
                collect(Collectors.toList());
        return rids;
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateRoleMenu(Integer rid,Integer[] mids){
        return menuRoleService.updateRoleMenu(rid,mids);
    }

}

package com.example.yebserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yebserver.pojo.Admin;
import com.example.yebserver.pojo.Menu;
import com.example.yebserver.pojo.RespBean;
import com.example.yebserver.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface IAdminService extends IService<Admin> {

    RespBean login(String username, String password,String code, HttpServletRequest request);

    Admin getAdminByUsername(String username);

    List<Role> getRoles(Integer adminId);


    List<Admin> getAllAdmin(String keywords);

    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}

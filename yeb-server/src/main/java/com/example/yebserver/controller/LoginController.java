package com.example.yebserver.controller;


import com.example.yebserver.pojo.Admin;
import com.example.yebserver.pojo.AdminLoginParam;
import com.example.yebserver.pojo.RespBean;
import com.example.yebserver.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }

    @ApiOperation(value = "获取当前用户信息")
    @PostMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if (principal==null){
            return null;
        }
        String username = principal.getName();
        Admin admin=adminService.getAdminByUsername(username);
        admin.setRoles(adminService.getRoles(admin.getId()));
        admin.setPassword(null);
        return admin;
    }
}

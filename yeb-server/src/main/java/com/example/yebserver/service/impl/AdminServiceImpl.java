package com.example.yebserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.yebserver.config.AdminUtils;
import com.example.yebserver.config.security.JwtTokenUtil;
import com.example.yebserver.mapper.AdminRoleMapper;
import com.example.yebserver.mapper.RoleMapper;
import com.example.yebserver.pojo.*;
import com.example.yebserver.mapper.AdminMapper;
import com.example.yebserver.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

//    @Autowired
//    private AdminMapper adminMapper;

    @Override
    public RespBean login(String username, String password,String code, HttpServletRequest request) {

        String captcha = (String) request.getSession().getAttribute("captcha");
        if(StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入");
        }

        UserDetails details = userDetailsService.loadUserByUsername(username);
        if(username==null || !passwordEncoder.matches(password,details.getPassword())){
            return  RespBean.error("用户名或密码不正确");
        }
        if (!details.isEnabled()){
            return RespBean.error("账号已被禁用，请联系管理员");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details,
                null, details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        HashMap<String, String> map = new HashMap<>();
        String token = jwtTokenUtil.generateToken(details);
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        return RespBean.success("登入成功",map);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        Admin admin = this.getOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled",true));
        return admin;
    }

    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    @Override
    public List<Admin> getAllAdmin(String keywords) {
        return adminMapper.getAllAdmin(AdminUtils.getAdmin().getId(), keywords);
    }

    @Override
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {

        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
//        if (StringUtils.isEmpty(rids) || rids.length==0){
//            return RespBean.success("更新成功");
//        }
        Integer integer = adminRoleMapper.addAdminRole(adminId, rids);
        if (integer==rids.length){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            if (adminMapper.updateById(admin)==1){
                return RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }


}

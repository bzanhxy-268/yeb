package com.example.yebserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yebserver.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}

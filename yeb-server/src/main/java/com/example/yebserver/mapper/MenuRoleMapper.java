package com.example.yebserver.mapper;

import com.example.yebserver.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer InsertRoleMenu(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}

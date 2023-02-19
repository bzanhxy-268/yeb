package com.example.yebserver.mapper;

import com.example.yebserver.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yebserver.pojo.Menu;
import com.example.yebserver.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface AdminMapper extends BaseMapper<Admin> {


    List<Admin> getAllAdmin(@Param("id") Integer id,@Param("keywords") String keywords);
}

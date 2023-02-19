package com.example.yebserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yebserver.pojo.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartment( Integer parentId);

    void addDepartment(Department department);

    void deleteDepartment(Department department);
}

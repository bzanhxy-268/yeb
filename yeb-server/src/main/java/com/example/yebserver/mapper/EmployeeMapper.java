package com.example.yebserver.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yebserver.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    IPage<Employee> getEmployee(Page<Employee> page,@Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> getEmp(@Param("id") Integer id);

    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}

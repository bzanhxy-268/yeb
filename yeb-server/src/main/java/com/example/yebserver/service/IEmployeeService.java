package com.example.yebserver.service;

import com.example.yebserver.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yebserver.pojo.RespBean;
import com.example.yebserver.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean maxWorkID();

    RespBean addEmp(Employee employee);

    List<Employee> getEmp(Integer id);

    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}

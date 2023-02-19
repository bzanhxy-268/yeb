package com.example.yebserver.service;

import com.example.yebserver.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yebserver.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllDepartment();

    RespBean addDepartment(Department department);

    RespBean deleteDepartment(Integer id);
}

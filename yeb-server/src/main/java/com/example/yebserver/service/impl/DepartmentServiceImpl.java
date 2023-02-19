package com.example.yebserver.service.impl;

import com.example.yebserver.pojo.Department;
import com.example.yebserver.mapper.DepartmentMapper;
import com.example.yebserver.pojo.RespBean;
import com.example.yebserver.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment(-1);
    }

    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if (department.getResult()==1){
            return RespBean.success("添加成功",department);
        }
        return RespBean.error("添加失败");
    }

    @Override
    public RespBean deleteDepartment(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDepartment(department);
        Integer result = department.getResult();
        if (result==-2){
            return RespBean.error("该部门下有子部门，删除失败");
        }
        if (result==-1){
            return RespBean.error("该部门下还有员工，删除失败");
        }
        if (result==1){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}

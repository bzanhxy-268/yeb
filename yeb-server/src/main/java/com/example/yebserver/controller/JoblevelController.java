package com.example.yebserver.controller;


import com.example.yebserver.pojo.Joblevel;
import com.example.yebserver.pojo.Position;
import com.example.yebserver.pojo.RespBean;
import com.example.yebserver.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value ="查询所有职称" )
    @GetMapping("/")
    public List<Joblevel> joblevels(){
        return joblevelService.list();
    }

    @ApiOperation(value ="添加职称" )
    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)){
            return RespBean.success("添加成功");
        }else
            return RespBean.error("添加失败");
    }

    @ApiOperation(value ="修改职称" )
    @PutMapping("/")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){
        if (joblevelService.updateById(joblevel)){
            return RespBean.success("更新成功");
        }else
            return RespBean.error("更新失败");
    }


    @ApiOperation(value ="删除职称" )
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(@PathVariable Integer id){
        if (joblevelService.removeById(id)){
            return RespBean.success("删除成功");
        }else
            return RespBean.error("删除失败");
    }

    @ApiOperation(value ="批量删除职称" )
    @DeleteMapping("/")
    public RespBean deletePositionByIds( Integer[] id){
        if (joblevelService.removeByIds(Arrays.asList(id))){
            return RespBean.success("删除成功");
        }else
            return RespBean.error("删除失败");
    }
}

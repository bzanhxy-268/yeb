package com.example.yebserver.controller;


import com.example.yebserver.pojo.Position;
import com.example.yebserver.pojo.RespBean;
import com.example.yebserver.service.IPositionService;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value ="查询所有职位" )
    @GetMapping("/")
    public List<Position> positions(){
        return positionService.list();
    }

    @ApiOperation(value ="添加职位" )
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)){
            return RespBean.success("添加成功");
        }else
            return RespBean.error("添加失败");
    }

    @ApiOperation(value ="修改职位" )
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updateById(position)){
            return RespBean.success("更新成功");
        }else
            return RespBean.error("更新失败");
    }


    @ApiOperation(value ="删除职位" )
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if (positionService.removeById(id)){
            return RespBean.success("删除成功");
        }else
            return RespBean.error("删除失败");
    }

    @ApiOperation(value ="批量删除职位" )
    @DeleteMapping("/")
    public RespBean deletePositionByIds( Integer[] id){
        if (positionService.removeByIds(Arrays.asList(id))){
            return RespBean.success("删除成功");
        }else
            return RespBean.error("删除失败");
    }



}

package com.example.yebserver.service.impl;

import com.example.yebserver.pojo.Oplog;
import com.example.yebserver.mapper.OplogMapper;
import com.example.yebserver.service.IOplogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hxg
 * @since 2022-11-13
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
